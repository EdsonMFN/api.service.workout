package api.workout.repositorys;

import api.workout.entitys.fichaDeTreino.ArquivoFichaTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBaixarTreino extends JpaRepository<ArquivoFichaTreino,Long> {

}
