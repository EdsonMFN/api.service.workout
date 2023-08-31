package api.workout.repositorys;

import api.workout.client.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuario extends JpaRepository<Usuario,Long> {

    Usuario findByNomeUsuario(String nomeUsuario);
}
