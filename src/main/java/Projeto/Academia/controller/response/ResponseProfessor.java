package Projeto.Academia.controller.response;

import Projeto.Academia.controller.DTO.ProfessorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProfessor {

    private ProfessorDTO professorDTO;

    public ResponseProfessor(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }
}
