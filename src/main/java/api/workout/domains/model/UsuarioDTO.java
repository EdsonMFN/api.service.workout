package api.workout.domains.model;

import api.workout.domains.entitys.Usuario;
import api.workout.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private Long id;
    private String nomeUsuario;
    private String senha;
    private UserRole role;

    public UsuarioDTO(Usuario usuario) {
        builder(usuario);
    }

    public UsuarioDTO builder(Usuario usuario){
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .senha(usuario.getSenha())
                .role(usuario.getRole())
                .build();
    }
}

