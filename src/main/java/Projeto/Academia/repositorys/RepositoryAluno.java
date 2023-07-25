package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepositoryAluno extends JpaRepository<Aluno,Long> {

    Optional<Aluno> findByCpf(String cpfAluno);

    List<Aluno> findAllByCpf(String cpfAluno);

    List<Aluno> findByAcademiaAfiliada(Academia academiaAfiliada);

    Optional<Aluno> getReferenceByCpf(String cpf);
}
