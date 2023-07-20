package Projeto.Academia.controller;

import Projeto.Academia.service.AlunoService;
import Projeto.Academia.controller.request.RequestAluno;
import Projeto.Academia.controller.response.ResponseAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/{idAcademia}/{idProfessor}")
    public ResponseEntity<ResponseAluno> criarAluno(@PathVariable Long idAcademia, @PathVariable Long idProfessor,@RequestBody RequestAluno requestAluno){

        ResponseAluno responseAlunoCriar = alunoService.criarAluno(idAcademia,idProfessor,requestAluno);

        return ResponseEntity.ok(responseAlunoCriar);
    }
}
