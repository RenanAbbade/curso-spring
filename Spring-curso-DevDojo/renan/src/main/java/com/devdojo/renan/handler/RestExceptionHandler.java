package com.devdojo.renan.handler;

import com.devdojo.renan.exception.BadRequestException;
import com.devdojo.renan.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //Estou dizendo a todos os controllers que eles devem utilizar as informações desta classe
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    //O intuito do Global Handler é prover uma response com detalhes em casos de erro
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, check the documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), (HttpStatus.BAD_REQUEST));
    }
}
