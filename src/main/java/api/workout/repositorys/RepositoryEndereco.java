package api.workout.repositorys;

import api.workout.entitys.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEndereco extends JpaRepository<Endereco,Long> {
}
