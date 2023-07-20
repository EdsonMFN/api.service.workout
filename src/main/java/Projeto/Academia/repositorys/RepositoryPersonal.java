package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.personal.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPersonal extends JpaRepository<Personal,Long> {
}
