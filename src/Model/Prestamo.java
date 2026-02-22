package Model;

import java.time.LocalDate;

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

    public boolean BloquePorDevolucion() { 

        if (fechaDevolucion == null) {
            return false; // Aquí el sistema detecta que no está devuelto
        }

        // Si el préstamo duró 30 días o más
        if (fechaDevolucion.isAfter(fechaPrestamo.plusDays(30))
                || fechaDevolucion.isEqual(fechaPrestamo.plusDays(30))) {

            // Aqui está el tiempo de bloqueo en caso de que se hayan superado los 30 días
            LocalDate finBloqueo = fechaDevolucion.plusDays(7);

            return LocalDate.now().isBefore(finBloqueo);
        }

        return false;
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