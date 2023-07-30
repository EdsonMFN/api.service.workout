package Projeto.Academia.exception;

import Projeto.Academia.controller.response.ResponseError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class TratamentoDeErrors extends ResponseEntityExceptionHandler{


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,HttpStatus status, WebRequest webRequest){

        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage(), "Argumento invalido");
         return handleExceptionInternal(ex,responseError,headers,status,webRequest);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest ){
        Object mensagem = null;

        if (ex.getCause() instanceof InvalidFormatException){
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            mensagem = exception.getValue();
        }
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST,"Resquest invalida");
        return new ResponseEntity<>(responseError,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ErrorException.class})
    public ResponseEntity<Object> handleApiException(ErrorException ex){

        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return new ResponseEntity<>(responseError,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex){

        ResponseError responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),"Erro na api");
        return new ResponseEntity<>(responseError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException ex, HttpStatus status){

        ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND,ex.getMessage(),"Entidade não encontrada");
        return new ResponseEntity<>(responseError,HttpStatus.NOT_FOUND);
    }
}
