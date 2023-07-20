package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.professor.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProfessor extends JpaRepository<Professor,Long> {
}
