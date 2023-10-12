package api.workout.exception;

import api.workout.rest.response.ResponseError;
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

@Slf4j(topic = "TRATAMENTO_HANDLER")
@RestControllerAdvice
public class TratamentoDeErrors extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest){
        ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST, "Argumento invalido", ex.getCause());
         return handleExceptionInternal(ex,responseError,headers,status,webRequest);
    }
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest ){
        log.error("Resquest invalido",ex.getCause());

        Object message = null;

        if (ex.getCause() instanceof InvalidFormatException){
            InvalidFormatException exception = (InvalidFormatException) ex.getCause();
            message = exception.getValue();
        }

        return buildErrorResponse(ex.getCause(),HttpStatus.BAD_REQUEST,"Resquest invalido");
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handlerObjectNotFoundException(ObjectNotFoundException ex,WebRequest webRequest){

        log.error("Falha ao encontar a requisicao", ex.getCause());

        return buildErrorResponse(ex.getCause(),HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler(DataBindingViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object>DataBindingViolationException(DataBindingViolationException ex,WebRequest webRequest){
        log.error("Não foi possível deletar por causa que existe entidades relacionadas", ex.getCause());

        return buildErrorResponse(ex.getCause(),HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex,WebRequest webRequest){

        logError(ex);
        log.error("Erro na api",ex.getCause());
        return buildErrorResponse(ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private static void logError(Exception ex) {
        log.error(ex.getClass().getName(), ex);
        log.error(ex.getClass().getName(), ex.getMessage());
        log.error(ex.getClass().getName(), ex.getLocalizedMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(
            Throwable message,
            HttpStatus httpStatus,
            String error) {
        ResponseError responseError = new ResponseError(httpStatus,error,message);
        return ResponseEntity.status(httpStatus).body(responseError);
    }

}
