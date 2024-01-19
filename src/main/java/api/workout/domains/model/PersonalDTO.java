package api.workout.domains.model;

import api.workout.domains.entitys.Personal;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO idAcademiasAfiliada;
    private List<AlunoDTO> alunos;

    public PersonalDTO(Personal personal) {
        builder(personal);
    }

    public PersonalDTO builder(Personal personal) {
        return PersonalDTO.builder()
            .id(personal.getId())
            .nome(personal.getNome())
            .cpf(personal.getCpf())
            .cref(personal.getCref())
            .idAcademiasAfiliada(new AcademiaDTO())
            .alunos(new ArrayList<>())
            .build();
    }
}
