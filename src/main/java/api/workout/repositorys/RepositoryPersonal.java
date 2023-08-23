package api.workout.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import api.workout.entitys.academia.Academia;
import api.workout.entitys.personal.Personal;

public interface RepositoryPersonal extends JpaRepository<Personal,Long> {

    List<Personal> findByAcademiaAfiliada(Academia idAcademia);

}
