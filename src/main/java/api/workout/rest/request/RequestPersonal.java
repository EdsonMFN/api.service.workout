package api.workout.rest.request;

import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RequestPersonal {

    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO academia;
    private List<AlunoDTO> alunos;
}
