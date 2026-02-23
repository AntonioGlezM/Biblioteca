package Main;

import Controlador.GestorBiblioteca;
import Model.*;
import Vista.Consola;

public class Main {

    public static void main(String[] args) {

        GestorBiblioteca gestor = new GestorBiblioteca();

        // Crear algunos libros de ejemplo
        gestor.agregarLibro(new Libro("1001", "Cien Años de Soledad", "Gabriel García Márquez", 1967, "Sudamericana",
                Genero.NOVELA, 4));
        gestor.agregarLibro(
                new Libro("1002", "Fundación", "Isaac Asimov", 1951, "Gnome Press", Genero.CIENCIA_FICCION, 6));
        gestor.agregarLibro(new Libro("1003", "El Principito", "Antoine de Saint-Exupéry", 1943, "Reynal & Hitchcock",
                Genero.INFANTIL, 5));
        gestor.agregarLibro(
                new Libro("1004", "El Hobbit", "J.R.R. Tolkien", 1937, "George Allen & Unwin", Genero.FANTASIA, 3));
        gestor.agregarLibro(new Libro("1005", "Sherlock Holmes: Estudio en Escarlata", "Arthur Conan Doyle", 1887,
                "Ward, Lock & Co.", Genero.MISTERIO, 2));

        // Crear algunos usuarios de ejemplo
        gestor.agregarUsuario(new Usuario("1", "Carlos"));
        gestor.agregarUsuario(new Usuario("2", "Lucía"));
        gestor.agregarUsuario(new Usuario("3", "Martín"));
        gestor.agregarUsuario(new Usuario("4", "Sofía"));

        // Crear la vista
        Consola vista = new Consola(gestor);

        // Mostrar menú
        vista.mostrarMenu();
    }
}