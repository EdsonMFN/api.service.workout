package Projeto.Academia.service.request;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.AlunoDTO;
import Projeto.Academia.controller.DTO.ProfessorDTO;
import Projeto.Academia.entitys.fichaDeTreino.Exercicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFichaDeTreino {

    private Exercicio exercicio;
    private ProfessorDTO idProfessor;
    private AlunoDTO cpfAluno;
    private AcademiaDTO idAcademiaAfiliada;
}
