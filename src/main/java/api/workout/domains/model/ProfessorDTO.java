package api.workout.domains.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO academiasAfiliada;
    private List<AlunoDTO> alunos;
    private List<FichaDeTreinoDTO> fichaDeTreinos;
}
