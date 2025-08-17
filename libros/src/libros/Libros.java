// Challenge Back End ONE "literalura"
//Daniela Joselin Falcon Sierra 

package libros;

import java.util.Scanner;

public class Libros {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LibroDAO libroDAO = new LibroDAO();
            int opcion = 0;
            
            while (opcion != 6) {
                System.out.println("\n------------- Menú Principal----------------");
                System.out.println("Elija la opción a través de su número:");
                System.out.println("1. Buscar libro por título");
                System.out.println("2. Listar libros registrados");
                System.out.println("3. Listar autores registrados");
                System.out.println("4. Listar autores vivos en un determinado año");
                System.out.println("5. Listar libros por idioma");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el título del libro a buscar: ");
                        String titulo = scanner.nextLine();
                        // Aquí deberíamos llamar a la API Gutendex (pendiente)
                        Autor autor = new Autor(0, "Jane", "Austen", 1775, 1817);
                        libroDAO.insertarLibro(titulo, "EN", 6493, autor);
                    }
                    case 2 -> {
                        System.out.println("📚 Libros registrados:");
                        libroDAO.listarLibros().forEach(l ->
                                System.out.println(l.getTitulo() + l.getAutor().getNombreCompleto() + " - ")
                        );
                    }
                    case 3 -> {
                        System.out.println("Listar autores (pendiente)");
                    }
                    case 4 -> {
                        System.out.println("Autores vivos en un año (pendiente)");
                    }
                    case 5 -> {
                        System.out.println("Libros por idioma (pendiente)");
                    }
                    case 6 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }
            }
        }
    }
}
