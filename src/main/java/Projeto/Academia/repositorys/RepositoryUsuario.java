package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.acesso.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<Usuario,Long>{

    Usuario findByNomeUsuario(String nomeUsuario);

    Usuario getReferenceByNomeUsuarioAndSenha(String nomeUsuario, String senha);




}
