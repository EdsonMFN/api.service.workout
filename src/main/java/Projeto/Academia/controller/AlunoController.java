package Projeto.Academia.controller;

import Projeto.Academia.service.AlunoService;
import Projeto.Academia.controller.request.RequestAluno;
import Projeto.Academia.controller.response.ResponseAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<ResponseAluno> criarAluno(@RequestBody RequestAluno requestAluno){

        ResponseAluno responseAlunoCriar = alunoService.criarAluno(requestAluno);

        return ResponseEntity.ok(responseAlunoCriar);
    }
    @GetMapping("/academia/{idAcademia}")
    public ResponseEntity<List<ResponseAluno>> listarAlunos(@PathVariable Long idAcademia){
        List<ResponseAluno> responseAlunos = alunoService.listarAlunos(idAcademia);
        return ResponseEntity.ok(responseAlunos);
    }
    @GetMapping("/{cpfAluno}")
    public ResponseEntity<ResponseAluno> buscarAluno(@PathVariable String cpfAluno){
        ResponseAluno responseAluno = alunoService.buscarAluno(cpfAluno);
        return ResponseEntity.ok(responseAluno);
    }
    @PutMapping
    public ResponseEntity<ResponseAluno> alterarAluno(@RequestBody RequestAluno requestAluno){
        ResponseAluno responseAluno = alunoService.alterarAluno(requestAluno);
        return ResponseEntity.ok(responseAluno);
    }
    @DeleteMapping("/{idAluno}")
    public ResponseEntity<ResponseAluno> deletarAluno(@PathVariable Long idAluno){
        ResponseAluno responseAluno = alunoService.deletarAluno(idAluno);
        return ResponseEntity.ok(responseAluno);
    }

}
