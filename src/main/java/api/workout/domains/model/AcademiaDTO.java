package api.workout.domains.model;

import api.workout.domains.entitys.Academia;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademiaDTO {

    private Long id;
    private String academiaAfiliada;
    private EnderecoDTO endereco;
    private String cnpj;
    private List<AlunoDTO> alunos;
    private List<ProfessorDTO> professores;
    private List<PersonalDTO> personals;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    public AcademiaDTO(Academia academia ) {
        builder(academia);
    }

    public AcademiaDTO builder(Academia academia) {
        return AcademiaDTO.builder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .endereco(new EnderecoDTO())
                .cnpj( academia.getCnpj())
                .alunos (new ArrayList<>())
                .professores(new ArrayList<>())
                .personals (new ArrayList<>())
                .fichaDeTreinos(new ArrayList<>())
                .build();
    }
}
