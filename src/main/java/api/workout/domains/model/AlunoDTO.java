package api.workout.domains.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private AcademiaDTO idAcademiaAfiliada;
    private ProfessorDTO crefProfessor;
    private PersonalDTO crefPersonal;
    private List<FichaDeTreinoDTO> fichaDeTreinos;
}
