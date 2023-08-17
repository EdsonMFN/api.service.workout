package Projeto.Academia.controller.response;

import Projeto.Academia.controller.DTO.UsuarioDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUsuario {

    private UsuarioDTO usuarioDTO;
    private String token;

    public ResponseUsuario(UsuarioDTO usuarioDTO,String token){

        this.usuarioDTO = usuarioDTO;
        this.token = token;
    }

    public ResponseUsuario(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }
}
