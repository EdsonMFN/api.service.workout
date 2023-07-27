package Projeto.Academia.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;

public interface RepositoryAluno extends JpaRepository<Aluno,Long> {

    Optional<Aluno> findByCpf(String cpfAluno);


    List<Aluno> findByAcademiaAfiliada(Academia academiaAfiliada);

    Optional<Aluno> getReferenceByCpf(String cpf);

}
