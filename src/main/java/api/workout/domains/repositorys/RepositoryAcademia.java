package api.workout.domains.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.domains.entitys.Academia;


public interface RepositoryAcademia extends JpaRepository<Academia,Long> {
}
