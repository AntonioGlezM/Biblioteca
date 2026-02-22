package Model;

public class Prestamo {

    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaVencimiento;
    private LocalDate fechaDevolucion;

    private static final int DIAS_PRESTAMO = 30;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
        this.fechaPrestamo = LocalDate.now();
        this.fechaVencimiento = fechaPrestamo.plusDays(DIAS_PRESTAMO);
    }

    public void devolver() {
        this.fechaDevolucion = LocalDate.now();
    }

    public boolean estaDevuelto() {
        return fechaDevolucion != null;
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(fechaVencimiento) && !estaDevuelto();
    }

    public Libro getLibro() {
        return libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
}