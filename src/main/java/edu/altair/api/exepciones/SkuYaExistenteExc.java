package edu.altair.api.exepciones;

public class SkuYaExistenteExc extends RuntimeException {

    public SkuYaExistenteExc(String mensaje) {
        super(mensaje);
    }
}