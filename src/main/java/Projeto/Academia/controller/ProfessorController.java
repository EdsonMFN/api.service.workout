package Projeto.Academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Projeto.Academia.controller.request.RequestProfessor;
import Projeto.Academia.controller.response.ResponseProfessor;
import Projeto.Academia.service.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ResponseProfessor> criarProfessor(@RequestBody RequestProfessor requestProfessor){
        ResponseProfessor responseProfessor = professorService.criarProfessor(requestProfessor);
        return ResponseEntity.ok(responseProfessor);
    }
    @GetMapping("/academia/{idAcademia}")
    public ResponseEntity<List<ResponseProfessor>> listarProfessor(@PathVariable Long idAcademia){
        List<ResponseProfessor> responseProfessores = professorService.listarProfessor(idAcademia);
        return ResponseEntity.ok(responseProfessores);
    }
    @GetMapping("/{idProfessor}")
    public ResponseEntity<ResponseProfessor> buscarProfessor(@PathVariable Long idProfessor){
            ResponseProfessor responseProfessor = professorService.buscarProfessor(idProfessor);
        return ResponseEntity.ok(responseProfessor);
    }
    @PutMapping
    public ResponseEntity<ResponseProfessor> alterarProfessor(@RequestBody RequestProfessor requestProfessor){
        ResponseProfessor responseProfessor = professorService.alterarProfessor(requestProfessor);
        return ResponseEntity.ok(responseProfessor);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<ResponseProfessor> deletarProfessor(@PathVariable Long idProfessor){
        ResponseProfessor responseProfessor = professorService.deletarProfessor(idProfessor);
        return ResponseEntity.ok(responseProfessor);
    }
}
