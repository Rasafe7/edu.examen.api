package edu.altair.api.exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcControladorGlobal {

    @ExceptionHandler(ProductoNoEncontradoExc.class)
    public ProblemDetail manejarProductoNoEncontrado(ProductoNoEncontradoExc ex) {

        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(SkuYaExistenteExc.class)
    public ProblemDetail manejarSkuYaExistente(SkuYaExistenteExc ex) {

        return ProblemDetail.forStatusAndDetail( HttpStatus.CONFLICT, ex.getMessage());
    }
}