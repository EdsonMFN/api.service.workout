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

    @PostMapping("/{idAcademia}/{idProfessor}")
    public ResponseEntity<ResponseAluno> criarAluno(@PathVariable Long idAcademia, @PathVariable Long idProfessor,@RequestBody RequestAluno requestAluno){

        ResponseAluno responseAlunoCriar = alunoService.criarAluno(idAcademia,idProfessor,requestAluno);

        return ResponseEntity.ok(responseAlunoCriar);
    }
    @GetMapping("/{idAcademia}")
    public ResponseEntity<List<ResponseAluno>> listarAlunos(@PathVariable Long idAcademia){
        List<ResponseAluno> responseAlunos = alunoService.listarAlunos(idAcademia);
        return ResponseEntity.ok(responseAlunos);
    }
    @GetMapping("/aluno/{cpfAluno}")
    public ResponseEntity<ResponseAluno> listarAlunos(@PathVariable String cpfAluno){
        ResponseAluno responseAluno = alunoService.buscarAluno(cpfAluno);
        return ResponseEntity.ok(responseAluno);
    }
    @PutMapping("/{idAcademia}")
    public ResponseEntity<ResponseAluno> alterarAluno(@PathVariable Long idAcademia,@RequestBody RequestAluno requestAluno){
        ResponseAluno responseAluno = alunoService.alterarAluno(idAcademia,requestAluno);
        return ResponseEntity.ok(responseAluno);
    }
    @DeleteMapping("/{idAluno}")
    public ResponseEntity<ResponseAluno> deletarAluno(@PathVariable Long idAluno){
        ResponseAluno responseAluno = alunoService.deletarAluno(idAluno);
        return ResponseEntity.ok(responseAluno);
    }
}
