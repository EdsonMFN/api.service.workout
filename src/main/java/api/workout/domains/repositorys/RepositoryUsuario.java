package api.workout.domains.repositorys;

import api.workout.domains.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<Usuario,Long> {

    Usuario findByNomeUsuario(String nomeUsuario);
}
