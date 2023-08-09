package Projeto.Academia.controller.request;

import Projeto.Academia.controller.DTO.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RequestPersonal {

    private Long idPersonal;
    private String nome;
    private String cpf;
    private String cref;
    private Long idAcademia;
    private List<AlunoDTO> cpfAluno;
}
