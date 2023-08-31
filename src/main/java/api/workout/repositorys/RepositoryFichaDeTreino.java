package api.workout.repositorys;

import api.workout.entitys.aluno.Aluno;
import api.workout.entitys.fichaDeTreino.FichaDeTreino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryFichaDeTreino extends JpaRepository<FichaDeTreino,Long> {

    List<FichaDeTreino> findByAluno(Aluno aluno);

}
