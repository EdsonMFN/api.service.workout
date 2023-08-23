package api.workout.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private Throwable message;
    private String error;
    private HttpStatus status;

    public ResponseError() {
        timestamp = LocalDateTime.now();
    }

    public ResponseError(HttpStatus status,  String error,Throwable message) {
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
