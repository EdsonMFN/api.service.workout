package api.workout.domains.repositorys;

import api.workout.domains.entitys.ArquivoFichaTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBaixarTreino extends JpaRepository<ArquivoFichaTreino,Long> {

}
