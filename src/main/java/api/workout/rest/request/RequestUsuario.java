package api.workout.rest.request;

import api.workout.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUsuario {

    private Long id;
    private String nomeUsuario;
    private String senha;
    private UserRole role;

}
