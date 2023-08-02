package Projeto.Academia.controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AcademiaDTO {

    private Long id;
    private String academiaAfiliada;
    private EnderecoDTO endereco;
    private String cnpj;
    private List<AlunoDTO> alunos;
    private List<ProfessorDTO> professores;
    private List<PersonalDTO> personals;
    private List<FichaDeTreinoDTO> fichaDeTreinos;
}
