package Projeto.Academia.controller.request;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RequestPersonal {

    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO idAcademiaAfiliada;
    private List<AlunoDTO> alunos;
}
