package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.fichaDeTreino.ArquivoFichaTreino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryBaixarTreino extends JpaRepository<ArquivoFichaTreino,Long> {

}
