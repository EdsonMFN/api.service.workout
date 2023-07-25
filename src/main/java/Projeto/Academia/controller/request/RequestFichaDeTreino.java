package Projeto.Academia.controller.request;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.AlunoDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;
import Projeto.Academia.entitys.fichaDeTreino.Exercicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFichaDeTreino {

    private Long id;
    private Exercicio exercicio;
    private ProfessorDTO professorDTO;
    private AlunoDTO alunoDTOluno;
    private AcademiaDTO academiaDTO;
}
