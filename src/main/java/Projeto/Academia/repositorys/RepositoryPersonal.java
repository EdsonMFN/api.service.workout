package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.personal.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryPersonal extends JpaRepository<Personal,Long> {


    List<Personal> findByAcademiaAfiliada(Academia idAcademia);
}
