package api.workout.domains.model;

import api.workout.domains.entitys.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private AcademiaDTO academiaAfiliada;
    private ProfessorDTO professor;
    private PersonalDTO personal;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    public AlunoDTO(Aluno aluno) {
        builder(aluno);
    }
    public AlunoDTO builder(Aluno aluno) {
        return  AlunoDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .cpf(aluno.getCpf())
                .academiaAfiliada(new AcademiaDTO())
                .professor(new ProfessorDTO())
                .personal(new PersonalDTO())
                .fichaDeTreinos(new ArrayList<>())
                .build();
    }
}
