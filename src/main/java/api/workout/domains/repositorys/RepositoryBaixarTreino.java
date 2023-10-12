package api.workout.domains.repositorys;

import api.workout.domains.entitys.fichaDeTreino.ArquivoFichaTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBaixarTreino extends JpaRepository<ArquivoFichaTreino,Long> {

}
