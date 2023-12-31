package api.workout.domains.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Aluno;

public interface RepositoryAluno extends JpaRepository<Aluno,Long> {

    Optional<Aluno> findByCpf(String cpfAluno);


    List<Aluno> findByAcademiaAfiliada(Academia academiaAfiliada);

    Optional<Aluno> getReferenceByCpf(String cpf);

}
