package api.workout.domains.model;

import api.workout.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Long idUsuario;
    private String nomeUsuario;
    private String senha;
    private UserRole role;

}

