package api.workout.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.entitys.academia.Academia;


public interface RepositoryAcademia extends JpaRepository<Academia,Long> {
}
