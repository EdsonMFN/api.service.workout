package Projeto.Academia.controller.request;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.PersonalDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;
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
