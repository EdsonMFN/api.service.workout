package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.acesso.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUsuario extends JpaRepository<Usuario, Long>{

    UserDetails findByNomeUsuario(String nomeUsuario);

    Usuario getReferenceByNomeUsuarioAndSenha(String nomeUsuario, String senha);
}
