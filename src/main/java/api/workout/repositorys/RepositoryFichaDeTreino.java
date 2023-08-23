package api.workout.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.entitys.aluno.Aluno;
import api.workout.entitys.fichaDeTreino.FichaDeTreino;

public interface RepositoryFichaDeTreino extends JpaRepository<FichaDeTreino,Long> {

    List<FichaDeTreino> findByAluno(Aluno cpfAluno);

}
