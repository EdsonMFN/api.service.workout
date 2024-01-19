package api.workout.domains.model;

import api.workout.domains.entitys.Professor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO academiasAfiliada;
    private List<AlunoDTO> alunos;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    public ProfessorDTO(Professor professor) {
        builder(professor);
    }

    public ProfessorDTO builder(Professor professor) {
        return ProfessorDTO.builder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(new AcademiaDTO())
                .alunos(new ArrayList<>())
                .fichaDeTreinos(new ArrayList<>())
                .build();
    }
}
