package api.workout.service;


import api.workout.client.FeignClient;
import api.workout.client.Usuario;
import api.workout.domains.model.UsuarioDTO;
import api.workout.domains.repositorys.RepositoryUsuario;
import api.workout.rest.request.RequestUsuario;
import api.workout.rest.response.ResponseUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService{

    @Autowired
    private FeignClient feignClient;

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public ResponseUsuario cadastrarUsuario(RequestUsuario requestUsuario, String authorization){

        String criptpgrafiaSenha = new BCryptPasswordEncoder().encode(requestUsuario.getSenha().trim());

        Usuario usuario = Usuario
                .builder()
                .nomeUsuario(requestUsuario.getNomeUsuario())
                .senha(criptpgrafiaSenha)
                .role(requestUsuario.getRole())
                .build();


        if (repositoryUsuario.findByNomeUsuario(usuario.getNomeUsuario()) != null){
            throw new RuntimeException("usuario existente");
        }
        repositoryUsuario.save(usuario);

        return new ResponseUsuario(UsuarioDTO.builder()
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