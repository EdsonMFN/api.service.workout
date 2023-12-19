package api.workout.exception.handles;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends EntityNotFoundException {
    public ObjectNotFoundException(String messagem){
        super(messagem);
    }
}
