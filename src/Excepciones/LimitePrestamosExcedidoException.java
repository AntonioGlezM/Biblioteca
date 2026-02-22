package Excepciones;

public class LimitePrestamosExcedidoException extends Exception {

    public LimitePrestamosExcedidoException(String mensaje) {
        super(mensaje);
    }
}