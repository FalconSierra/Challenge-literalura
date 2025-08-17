package libros;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public void insertarLibro(String titulo, String idioma, int descargas, Autor autor) {
        try (Connection conn = Conexion.conectar()) {
            if (conn == null) return;

            // Verificar si ya existe
            PreparedStatement check = conn.prepareStatement("SELECT * FROM libro WHERE titulo = ?");
            check.setString(1, titulo);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                System.out.println("⚠️ El libro ya está registrado.");
                return;
            }

            // Insertar autor si no existe
            PreparedStatement checkAutor = conn.prepareStatement(
                "SELECT id_autor FROM autor WHERE nombre = ? AND apellido = ?");
            checkAutor.setString(1, autor.getNombreCompleto().split(", ")[1]);
            checkAutor.setString(2, autor.getNombreCompleto().split(", ")[0]);
            ResultSet rsAutor = checkAutor.executeQuery();
            int idAutor;
            if (rsAutor.next()) {
                idAutor = rsAutor.getInt("id_autor");
            } else {
                PreparedStatement insertAutor = conn.prepareStatement(
                    "INSERT INTO autor (nombre, apellido, fecha_nacimiento, fecha_fallecimiento) VALUES (?,?,?,?) RETURNING id_autor");
                insertAutor.setString(1, autor.getNombreCompleto().split(", ")[1]);
                insertAutor.setString(2, autor.getNombreCompleto().split(", ")[0]);
                insertAutor.setObject(3, autor.fechaNacimiento);
                insertAutor.setObject(4, autor.fechaFallecimiento);
                ResultSet newAutor = insertAutor.executeQuery();
                newAutor.next();
                idAutor = newAutor.getInt("id_autor");
            }

            // Insertar libro
            PreparedStatement insertLibro = conn.prepareStatement(
                "INSERT INTO libro (titulo, idioma, numero_descargas, id_autor) VALUES (?,?,?,?)");
            insertLibro.setString(1, titulo);
            insertLibro.setString(2, idioma);
            insertLibro.setInt(3, descargas);
            insertLibro.setInt(4, idAutor);
            insertLibro.executeUpdate();

            System.out.println(" Libro registrado con éxito.");

        } catch (SQLException e) {
            System.out.println("Error al insertar libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        List<Libro> lista = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            if (conn == null) return lista;

            String sql = "SELECT l.id_libro, l.titulo, l.idioma, l.numero_descargas, a.id_autor, a.nombre, a.apellido " +
                         "FROM libro l JOIN autor a ON l.id_autor = a.id_autor";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Autor autor = new Autor(
                    rs.getInt("id_autor"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    null, null
                );
                Libro libro = new Libro(
                    rs.getInt("id_libro"),
                    rs.getString("titulo"),
                    rs.getString("idioma"),
                    rs.getInt("numero_descargas"),
                    autor
                );
                lista.add(libro);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
        return lista;
    }
}
