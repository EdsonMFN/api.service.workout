package api.workout.exception.handles;

import jakarta.persistence.EntityNotFoundException;

public class ErrorException extends RuntimeException{
    public ErrorException(String s) {
        super(s);
    }
    public ErrorException(String s, Throwable throwable){super(s,throwable);}
}
