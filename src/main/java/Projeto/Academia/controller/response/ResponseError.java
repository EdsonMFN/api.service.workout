package Projeto.Academia.controller.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseError {

    private LocalDateTime timestamp;
    private String message;
    private String error;
    private HttpStatus status;

    public ResponseError() {
        timestamp = LocalDateTime.now();
    }

    public ResponseError(HttpStatus status, String message, String error) {
        this();
        this.status = status;
        this.error = error;
        this.message = message ;
    }

    public ResponseError(HttpStatus status,String error) {
        this();
        this.status = status;
        this.error = error ;
    }
}
