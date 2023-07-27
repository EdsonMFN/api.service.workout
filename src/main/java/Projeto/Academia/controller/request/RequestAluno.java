package Projeto.Academia.controller.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestAluno {

    private String nome;
    private String cpf;
    private Long idAcademia;
    private Long idProfessor;
    private Long idPersonal;

}
