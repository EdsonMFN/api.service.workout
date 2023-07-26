package Projeto.Academia.service.request;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.PersonalDTO;
import Projeto.Academia.controller.DTO.ProfessorDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestAluno {

    private String nome;
    private String cpf;
    private AcademiaDTO idAcademiaAfiliada;
    private ProfessorDTO idProfessor;
    private PersonalDTO idPersonal;
}
