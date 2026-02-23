package Controlador;

import Model.*;
import Excepciones.*;

import java.util.ArrayList;
import java.util.List;

public class GestorBiblioteca {

    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public GestorBiblioteca() {
        libros = new ArrayList<>();
        usuarios = new ArrayList<>();
        prestamos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void prestarLibro(String isbn, String idUsuario)
            throws LibroNoDisponibleException,
            LimitePrestamosExcedidoException {

        Libro libro = buscarLibroPorISBN(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null || usuario == null) {
            throw new IllegalArgumentException("Libro o usuario no válido.");
        }

        if (libro.getCopiasDisponibles() <= 0 ||
                libro.getEstado() == EstadoLibro.RESERVADO) {
            throw new LibroNoDisponibleException("El libro no está disponible.");
        }

        if (usuario.getLibrosPrestados().size() >= 3) {
            throw new LimitePrestamosExcedidoException(
                    "El usuario ya tiene 3 libros prestados.");
        }

        for (Prestamo p : prestamos) {
            if (p.getLibro().getIsbn().equals(isbn) &&
                    p.getUsuario().getId().equals(idUsuario)) {
                if (p.BloquePorDevolucion()) {
                    throw new IllegalArgumentException(
                            "Debe esperar 7 días para volver a pedir este libro.");
                }
            }
        }

        Prestamo prestamo = new Prestamo(libro, usuario);
        usuario.prestarLibro(libro);
        libro.reducirCopia();
        prestamos.add(prestamo);
    }

    public void devolverLibro(String isbn, String idUsuario) {

        for (Prestamo p : prestamos) {
            if (p.getLibro().getIsbn().equals(isbn) &&
                    p.getUsuario().getId().equals(idUsuario) &&
                    !p.estaDevuelto()) {

                p.devolver();
                p.getLibro().aumentarCopia();
                p.getUsuario().devolverLibro(p.getLibro());
                break;
            }
        }
    }

    public void reservarLibro(String isbn, String idUsuario) throws LibroNoDisponibleException {
        Libro libro = buscarLibroPorISBN(isbn);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro == null || usuario == null) {
            throw new IllegalArgumentException("Libro o usuario no válido.");
        }

        if (libro.getEstado() != EstadoLibro.DISPONIBLE) {
            throw new LibroNoDisponibleException("El libro no se puede reservar.");
        }

        libro.reservar();
        System.out.println("Libro '" + libro.getTitulo() + "' reservado por " + usuario.getNombre());
    }

    public Libro buscarLibroPorISBN(String isbn) {
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return l;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
    // Con este método, usamos una lista para obtener todos los libros con el mismo título, y nos devuelve el resultado con todos ellos.
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // buscar libros por género, de la misma manera que con el título.
    public List<Libro> buscarLibrosPorGenero(Genero genero) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getGenero() == genero) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    // Este método nos indica que usuario tiene cada libro.
    public Usuario usuarioConLibroPrestado(String isbn) {
        for (Prestamo p : prestamos) {
            if (p.getLibro().getIsbn().equals(isbn) && !p.estaDevuelto()) {
                return p.getUsuario(); // devuelve el usuario que tiene el libro
            }
        }
        return null; // si nadie tiene el libro prestado
    }

    public void resumenLibros() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public void resumenUsuarios() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }
}