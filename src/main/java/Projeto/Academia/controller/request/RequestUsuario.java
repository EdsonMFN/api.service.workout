package Projeto.Academia.controller.request;

import Projeto.Academia.entitys.acesso.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUsuario {

    private Long idUsuario;
    private String nomeUsuario;
    private String senha;
    private UserRole role;

}
