package Vista;

import Model.*;
import Controlador.GestorBiblioteca;
import Excepciones.*;

import java.util.List;
import java.util.Scanner;

public class Consola {

    private GestorBiblioteca gestor;
    private Scanner sc;

    public Consola(GestorBiblioteca gestor) {
        this.gestor = gestor;
        this.sc = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n--- MENÚ BIBLIOTECA ---");
            System.out.println("1. Prestar un libro");
            System.out.println("2. Devolver un libro");
            System.out.println("3. Reservar un libro");
            System.out.println("4. Buscar libro por Título");
            System.out.println("5. Buscar libro por ISBN");
            System.out.println("6. Buscar libro por Género");
            System.out.println("7. Identificar usuario con libro prestado");
            System.out.println("8. Resumen de libros");
            System.out.println("9. Resumen de usuarios");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    prestarLibro();
                    break;
                case 2:
                    devolverLibro();
                    break;
                case 3:
                    reservarLibro();
                    break;
                case 4:
                    buscarPorTitulo();
                    break;
                case 5:
                    buscarPorISBN();
                    break;
                case 6:
                    buscarPorGenero();
                    break;
                case 7:
                    usuarioConLibro();
                    break;
                case 8:
                    gestor.resumenLibros();
                    break;
                case 9:
                    gestor.resumenUsuarios();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void prestarLibro() {
        try {
            System.out.print("ISBN del libro: ");
            String isbn = sc.nextLine();
            System.out.print("ID del usuario: ");
            String idUsuario = sc.nextLine();

            gestor.prestarLibro(isbn, idUsuario);
            System.out.println("Libro prestado correctamente.");

        } catch (LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void devolverLibro() {
        System.out.print("ISBN del libro: ");
        String isbn = sc.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = sc.nextLine();

        gestor.devolverLibro(isbn, idUsuario);
        System.out.println("Libro devuelto correctamente.");
    }

    private void reservarLibro() {
        try {
            System.out.print("ISBN del libro: ");
            String isbn = sc.nextLine();
            System.out.print("ID del usuario: ");
            String idUsuario = sc.nextLine();

            gestor.reservarLibro(isbn, idUsuario);

        } catch (LibroNoDisponibleException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarPorTitulo() {
        System.out.print("Título del libro: ");
        String titulo = sc.nextLine();

        List<Libro> encontrados = gestor.buscarLibrosPorTitulo(titulo);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            for (Libro l : encontrados) {
                System.out.println(l);
            }
        }
    }

    private void buscarPorISBN() {
        System.out.print("ISBN del libro: ");
        String isbn = sc.nextLine();

        Libro libro = gestor.buscarLibroPorISBN(isbn);
        if (libro != null) {
            System.out.println(libro);
        } else {
            System.out.println("No se encontró el libro.");
        }
    }

    private void buscarPorGenero() {
        System.out.println("Géneros disponibles: NOVELA, CIENCIA_FICCION, HISTORIA, FANTASIA, MISTERIO, INFANTIL, POESIA, OTRO");
        System.out.print("Género: ");
        String generoStr = sc.nextLine().toUpperCase();

        try {
            Genero genero = Genero.valueOf(generoStr);
            List<Libro> encontrados = gestor.buscarLibrosPorGenero(genero);

            if (encontrados.isEmpty()) {
                System.out.println("No se encontraron libros de ese género.");
            } else {
                for (Libro l : encontrados) {
                    System.out.println(l);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Género inválido.");
        }
    }

    private void usuarioConLibro() {
        System.out.print("ISBN del libro: ");
        String isbn = sc.nextLine();

        Usuario usuario = gestor.usuarioConLibroPrestado(isbn);
        if (usuario != null) {
            System.out.println("El libro está prestado a: " + usuario.getNombre());
        } else {
            System.out.println("El libro no está prestado actualmente.");
        }
    }
}