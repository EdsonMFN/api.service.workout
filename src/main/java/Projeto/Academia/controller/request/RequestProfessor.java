package Projeto.Academia.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProfessor {

    private String nome;
    private String cpf;
    private String cref;
    private Long idAcademia;

}
