package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.UsuarioDTO;
import Projeto.Academia.controller.request.RequestUsuario;
import Projeto.Academia.controller.response.ResponseUsuario;
import Projeto.Academia.entitys.acesso.Usuario;
import Projeto.Academia.repositorys.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public ResponseUsuario acessarLogin(RequestUsuario requestUsuario){
        Usuario usuario = repositoryUsuario.getReferenceByNomeUsuarioAndSenha
                (requestUsuario.getNomeUsuario(), requestUsuario.getSenha());

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .id(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .senha(usuario.getSenha())
                .build();

        var usuarioAcesso = new UsernamePasswordAuthenticationToken(usuarioDTO.getNomeUsuario(),usuarioDTO.getSenha());
        var acesso = this.authenticationManager.authenticate(usuarioAcesso);
        var token = tokenService.gerarToken((Usuario) acesso.getPrincipal());

        ResponseUsuario responseUsuario = new ResponseUsuario();
        responseUsuario.setUsuarioDTO(usuarioDTO);
        responseUsuario.setToken(token);

        return responseUsuario;
    }
    public ResponseUsuario cadastroLogin(RequestUsuario requestUsuario){

        Usuario usuario = Usuario
                .builder()
                .nomeUsuario(requestUsuario.getNomeUsuario())
                .senha(requestUsuario.getSenha())
                .role(requestUsuario.getRole())
                .build();

        if (this.repositoryUsuario.findByNomeUsuario(usuario.getNomeUsuario()) != null){
            throw new RuntimeException("usuario existente");
        }repositoryUsuario.save(usuario);

        String criptpgrafiaSenha = new BCryptPasswordEncoder().encode(usuario.getSenha());

        return new ResponseUsuario (UsuarioDTO.builder()
                .id(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .senha(criptpgrafiaSenha)
                .role(usuario.getRole())
                .build());
    }
}
