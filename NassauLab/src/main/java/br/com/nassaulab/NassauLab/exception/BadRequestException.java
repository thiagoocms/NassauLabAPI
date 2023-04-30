package br.com.nassaulab.NassauLab.exception;

import java.util.List;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }

}
