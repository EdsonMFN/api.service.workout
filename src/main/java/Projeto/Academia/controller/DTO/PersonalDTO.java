package Projeto.Academia.controller.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonalDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO idAcademiasAfiliada;
    private List<AlunoDTO> alunos;
}
