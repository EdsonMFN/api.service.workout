package Projeto.Academia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;

public interface RepositoryFichaDeTreino extends JpaRepository<FichaDeTreino,Long> {

    List<FichaDeTreino> findByAluno(Aluno cpfAluno);

}
