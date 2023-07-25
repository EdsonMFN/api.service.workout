package Projeto.Academia.controller;

import Projeto.Academia.service.ProfessorService;
import Projeto.Academia.controller.request.RequestProfessor;
import Projeto.Academia.controller.response.ResponseProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/{idAcademia}")
    public ResponseEntity<ResponseProfessor> criarProfessor(@PathVariable Long idAcademia, @RequestBody RequestProfessor requestProfessor){

        ResponseProfessor responseProfessor = professorService.criarProfessor(idAcademia, requestProfessor);

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
    @PutMapping("/{idAcademia}")
    public ResponseEntity<ResponseProfessor> alterarProfessor(@PathVariable Long idAcademia,@RequestBody RequestProfessor requestProfessor){
        ResponseProfessor responseProfessor = professorService.alterarProfessor(idAcademia,requestProfessor);
        return ResponseEntity.ok(responseProfessor);
    }

    @DeleteMapping("/{idProfessor}")
    public ResponseEntity<ResponseProfessor> deletarProfessor(@PathVariable Long idProfessor){

        ResponseProfessor responseProfessor = professorService.deletarProfessor(idProfessor);
        return ResponseEntity.ok(responseProfessor);
    }
}
