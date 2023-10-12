package api.workout.domains.repositorys;

import api.workout.domains.entitys.academia.Academia;
import api.workout.domains.entitys.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryProfessor extends JpaRepository<Professor,Long> {


    List<Professor> findByAcademiaAfiliada(Academia academiaAfiliada);

    List<Professor> findAllByAcademiaAfiliada(Academia academiaAfiliada);

    Professor findByNome(String nomeProfessor);
}
