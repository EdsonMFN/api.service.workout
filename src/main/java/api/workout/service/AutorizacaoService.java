package api.workout.service;

import api.workout.client.Usuario;
import api.workout.repositorys.RepositoryUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizacaoService implements UserDetailsService {

    @Autowired
    RepositoryUsuario repositoryUsuario;

    @Override
    public Usuario loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        return repositoryUsuario.findByNomeUsuario(nomeUsuario);

    }

}
