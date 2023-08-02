package Projeto.Academia.exception;

import Projeto.Academia.controller.response.ResponseError;
import Projeto.Academia.service.exception.DataBindingViolationException;
import Projeto.Academia.service.exception.ErrorException;
import Projeto.Academia.service.exception.ObjectNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@Slf4j(topic = "TRATAMENTO_HANDLER")
@RestControllerAdvice
public class TratamentoDeErrors extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest){
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST, "Argumento invalido", ex.getMessage());
         return handleExceptionInternal(ex,responseError,headers,status,webRequest);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest ){
        Object message = null;

        if (ex.getCause() instanceof InvalidFormatException){
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            message = exception.getValue();
        }
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST,"Resquest invalido", Optional.ofNullable(message).map(m -> m.toString()).orElse(message.toString()));
        return new ResponseEntity<>(responseError,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Object> handleApiException(ErrorException ex){
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST,ex.getMessage());
        return new ResponseEntity<>(responseError,new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex,WebRequest webRequest){
        String erroMensage = "Erro na api";
        log.error(erroMensage,ex.getMessage());
        return buildErrorResponse(ex, erroMensage, HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlerObjectNotFoundException(ObjectNotFoundException ex,WebRequest webRequest){

        log.error("Falha ao encontar a requisicao", ex.getCause());

        return buildErrorResponse(ex,HttpStatus.NOT_FOUND,webRequest);
    }
    @ExceptionHandler(DataBindingViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object>DataBindingViolationException(DataBindingViolationException ex,WebRequest webRequest){
        log.error("Não foi possível deletar por causa que existe entidades relacionadas", ex.getCause());

        return buildErrorResponse(ex,HttpStatus.CONFLICT,webRequest);
    }
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus httpStatus,
            WebRequest request) {
        return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
    }
    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus httpStatus,
            WebRequest request) {
        ResponseError responseError = new ResponseError(httpStatus, message);
        return ResponseEntity.status(httpStatus).body(responseError);
    }

}
