package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFichaDeTreino extends JpaRepository<FichaDeTreino,Long> {
}
