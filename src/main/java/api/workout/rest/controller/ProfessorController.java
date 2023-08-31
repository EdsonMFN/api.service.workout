package api.workout.rest.controller;

import api.workout.rest.request.RequestProfessor;
import api.workout.rest.response.ResponseProfessor;
import api.workout.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Cacheable(value = "professor")
    public ResponseEntity<ResponseProfessor> buscarProfessor(@PathVariable Long idProfessor){
            ResponseProfessor responseProfessor = professorService.buscarProfessor(idProfessor);
        return ResponseEntity.ok(responseProfessor);
    }
    @PutMapping
    @CachePut(value = "professor")
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
