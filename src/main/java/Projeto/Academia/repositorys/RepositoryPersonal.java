package Projeto.Academia.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.personal.Personal;

public interface RepositoryPersonal extends JpaRepository<Personal,Long> {

    List<Personal> findByAcademiaAfiliada(Academia idAcademia);

}
