package Com.Renan.Spring.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
//Esta classe efetua o tratamento de erros nos controllers da aplicação
//Vai interceptar as exceptions
import org.springframework.web.bind.annotation.ExceptionHandler;

import Com.Renan.Spring.exceptions.DataIntegrityException;
import Com.Renan.Spring.exceptions.ObjectNotFoundException;
import Com.Renan.Spring.exceptions.StandardError;
import Com.Renan.Spring.exceptions.ValidationError;

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

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
    ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());

    //Percorrer lista erros na Exception padrao, pegando somente o nome do campo e a mensagem correspondente
    for(FieldError x :  e.getBindingResult().getFieldErrors()){//e.getBindingResult().getFieldError() -> verifico todos os erros de campos do runtimeError em questão
      err.addError(x.getField(), x.getDefaultMessage());

    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }


}