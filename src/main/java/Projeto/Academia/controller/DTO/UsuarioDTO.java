package Projeto.Academia.controller.DTO;

import Projeto.Academia.entitys.acesso.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO {

    private Long id;

    private String nomeUsuario;

    private String senha;

    private UserRole role;

}

