package Projeto.Academia.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import Projeto.Academia.entitys.academia.Academia;


public interface RepositoryAcademia extends JpaRepository<Academia,Long> {
}
