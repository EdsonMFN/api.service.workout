package api.workout.domains.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Personal;

public interface RepositoryPersonal extends JpaRepository<Personal,Long> {

    List<Personal> findByAcademiaAfiliada(Academia idAcademia);

}
