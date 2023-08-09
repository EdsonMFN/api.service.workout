package Projeto.Academia.controller.response;

import Projeto.Academia.controller.DTO.AlunoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAluno {

    private AlunoDTO alunoDTO;

    public ResponseAluno(AlunoDTO alunoDTO) {
        this.alunoDTO=alunoDTO;
    }
}
