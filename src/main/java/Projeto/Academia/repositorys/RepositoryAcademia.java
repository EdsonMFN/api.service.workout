package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryAcademia extends JpaRepository<Academia,Long> {
}
