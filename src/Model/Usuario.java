package Model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String id;
    private String nombre;
    private List<Libro> librosPrestados;
    private List<Libro> prestamosActivos;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.librosPrestados = new ArrayList<>();
        this.prestamosActivos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public List<Libro> getprestamosActivos() {
        return prestamosActivos;
    }

    public void prestarLibro(Libro libro) {

        if (prestamosActivos.size() < 3) {
            prestamosActivos.add(libro);
        } else {
            System.out.println("No puede tener más de 3 libros prestados.");
        }
    }

    public void devolverLibro(Libro libro) {
        prestamosActivos.remove(libro);
        librosPrestados.add(libro);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " | Libros prestados: " + prestamosActivos.size();
    }
}