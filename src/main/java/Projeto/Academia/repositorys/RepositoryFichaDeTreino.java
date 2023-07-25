package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryFichaDeTreino extends JpaRepository<FichaDeTreino,Long> {

    List<FichaDeTreino> findByAluno(Aluno cpfAluno);
}
