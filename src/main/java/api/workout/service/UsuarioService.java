package api.workout.service;


import api.workout.client.UserClient;
import api.workout.domains.entitys.Usuario;
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
    private UserClient userClient;

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public ResponseUsuario cadastrarUsuario(RequestUsuario requestUsuario, String authorization){

        String criptpgrafiaSenha = new BCryptPasswordEncoder().encode(requestUsuario.getSenha().trim());

        Usuario usuario =new Usuario(requestUsuario);

        if (repositoryUsuario.findByNomeUsuario(usuario.getNomeUsuario()) != null){
            throw new RuntimeException("usuario existente");
        }
        repositoryUsuario.save(usuario);

        return new ResponseUsuario(new UsuarioDTO(usuario));

    }
    public List<ResponseUsuario> listarUsuarios() {
        List<Usuario> usuarios = repositoryUsuario.findAll();

        List<ResponseUsuario> responseUsuarios = new ArrayList<>();

        usuarios.parallelStream().forEach(usuario -> {

            ResponseUsuario responseUsuario = new ResponseUsuario(new UsuarioDTO(usuario));

            responseUsuarios.add(responseUsuario);
        });

        return responseUsuarios;
    }

}
