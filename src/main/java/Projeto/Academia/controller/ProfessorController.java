package Projeto.Academia.controller;

import Projeto.Academia.service.ProfessorService;
import Projeto.Academia.controller.request.RequestProfessor;
import Projeto.Academia.controller.response.ResponseProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
