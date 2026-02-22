package Model;

public class Libro {

    private String isbn;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String editorial;
    private Genero genero;
    private int copiasTotales;
    private int copiasDisponibles;
    private EstadoLibro estado;

    public Libro(String isbn, String titulo, String autor,
            int anioPublicacion, String editorial,
            Genero genero, int copiasTotales) {

        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.genero = genero;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasTotales;
        this.estado = EstadoLibro.DISPONIBLE;
    }

    public void actualizarEstado() {
        if (copiasDisponibles == 0) {
            estado = EstadoLibro.PRESTADO;
        } else {
            estado = EstadoLibro.DISPONIBLE;
        }
    }

    public void reducirCopia() {
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            actualizarEstado();
        }
    }

    public void aumentarCopia() {
        if (copiasDisponibles < copiasTotales) {
            copiasDisponibles++;
            actualizarEstado();
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public EstadoLibro getEstado() {
        return estado;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    @Override
    public String toString() {
        return titulo + " | ISBN: " + isbn +
                " | Estado: " + estado +
                " | Disponibles: " + copiasDisponibles;
    }
}