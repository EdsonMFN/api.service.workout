package Projeto.Academia.repositorys;

import Projeto.Academia.entitys.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEndereco extends JpaRepository<Endereco,Long> {
}
