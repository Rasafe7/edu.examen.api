package edu.altair.api.exepciones;

public class ProductoNoEncontradoExc extends RuntimeException {

    public ProductoNoEncontradoExc(String mensaje) {
        super(mensaje);
    }
}