package Com.Renan.Spring.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
//Esta classe efetua o tratamento de erros nos controllers da aplicação
//Vai interceptar as exceptions
import org.springframework.web.bind.annotation.ExceptionHandler;

import Com.Renan.Spring.exceptions.DataIntegrityException;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;
import Com.Renan.Spring.exceptions.StandardError;

@ControllerAdvice
public class ResourceExceptionHandler{

  
  @ExceptionHandler(ObjectNotFoundException.class)//Indica que é um tratador de Exceptions, e o .class o tipo 
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
    //Standard error passa como parametro o número que representa o resultado da requisição, a mensagem e o tempo atual.
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    

  }

  @ExceptionHandler(DataIntegrityException.class)//Indica que é um tratador de Exceptions, e o .class o tipo 
  public ResponseEntity<StandardError> DataIntegrity(DataIntegrityException e, HttpServletRequest request){
    //Gera-se um BAD Request, pois foi uma tentativa de exclusão de uma entidade diretamente relacionada com outra
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    

  }



}