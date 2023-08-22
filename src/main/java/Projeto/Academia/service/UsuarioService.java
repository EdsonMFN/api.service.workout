package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.UsuarioDTO;
import Projeto.Academia.controller.request.RequestUsuario;
import Projeto.Academia.controller.response.ResponseUsuario;
import Projeto.Academia.entitys.acesso.Usuario;
import Projeto.Academia.repositorys.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private RepositoryUsuario repositoryUsuario;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AutorizacaoService autorizacaoService;

    public ResponseUsuario acessarLogin(RequestUsuario requestUsuario){

        Usuario usuario = autorizacaoService.loadUserByUsername(requestUsuario.getNomeUsuario());

        var usuarioAcesso = new UsernamePasswordAuthenticationToken(requestUsuario.getNomeUsuario(),requestUsuario.getSenha());
        var acesso = authenticationManager.authenticate(usuarioAcesso);
        var token = tokenService.gerarToken(usuario);
        var tokenRefresh = tokenService.gerarRefreshToken(usuario);
        SecurityContextHolder.getContext().setAuthentication(acesso);

        UsuarioDTO usuarioDTO = UsuarioDTO.builder()
                .idUsuario(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .senha(usuario.getSenha())
                .role(usuario.getRole())
                .build();

        ResponseUsuario responseUsuario = new ResponseUsuario();
        responseUsuario.setUsuarioDTO(usuarioDTO);
        responseUsuario.setToken(token);
        responseUsuario.setTokenRefresh(tokenRefresh);

        return responseUsuario;
    }
    public ResponseUsuario cadastroLogin(RequestUsuario requestUsuario){
        String criptpgrafiaSenha = new BCryptPasswordEncoder().encode(requestUsuario.getSenha().trim());

        Usuario usuario = Usuario
                .builder()
                .nomeUsuario(requestUsuario.getNomeUsuario())
                .senha(criptpgrafiaSenha)
                .role(requestUsuario.getRole())
                .build();


        if (this.repositoryUsuario.findByNomeUsuario(usuario.getNomeUsuario()) != null){
            throw new RuntimeException("usuario existente");
        }
        repositoryUsuario.save(usuario);

        return new ResponseUsuario (UsuarioDTO.builder()
                .idUsuario(usuario.getId())
                .nomeUsuario(usuario.getNomeUsuario())
                .senha(criptpgrafiaSenha)
                .role(usuario.getRole())
                .build());

    }
    public List<ResponseUsuario> listarUsuarios() {
        List<Usuario> usuarios = repositoryUsuario.findAll();

        List<ResponseUsuario> responseUsuarios = new ArrayList<>();

        usuarios.parallelStream().forEach(usuario -> {

            ResponseUsuario responseUsuario = new ResponseUsuario(UsuarioDTO.builder()
                    .nomeUsuario(usuario.getNomeUsuario())
                    .idUsuario(usuario.getId())
                    .role(usuario.getRole())
                    .build());

            responseUsuarios.add(responseUsuario);
        });

        return responseUsuarios;
    }
}
