package api.workout.domains.repositorys;

import api.workout.domains.entitys.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEndereco extends JpaRepository<Endereco,Long> {
}
