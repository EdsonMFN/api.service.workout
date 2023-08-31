package api.workout.rest.controller;

import api.workout.rest.request.RequestAcademia;
import api.workout.rest.response.ResponseAcademia;
import api.workout.service.AcademiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academia")
public class AcademiaController {

    @Autowired
    private AcademiaService academiaService;

    @PostMapping
    public ResponseEntity<ResponseAcademia> criarAcademia(@RequestBody RequestAcademia requestAcademia){
        ResponseAcademia responseAcademiaCriar = academiaService.criarAcademia(requestAcademia);
        return ResponseEntity.ok(responseAcademiaCriar);
    }
    @GetMapping
    public ResponseEntity<List<ResponseAcademia>> listarAcademia(){
        List<ResponseAcademia> responseAcademiaListar = academiaService.listarAcademia();
        return ResponseEntity.ok(responseAcademiaListar);
    }
    @GetMapping("/{idAcademia}")
    @Cacheable(value = "academia")
    public ResponseEntity<ResponseAcademia> buscarAcademia(@PathVariable Long idAcademia){
            ResponseAcademia responseAcademiaBuscar = academiaService.buscarAcademia(idAcademia);
        return ResponseEntity.ok(responseAcademiaBuscar);
    }
    @PutMapping
    @CachePut(value = "academia")
    public ResponseEntity<ResponseAcademia> alterarAcademia(@RequestBody RequestAcademia requestAcademia) {
        ResponseAcademia responseAcademiaAlterar = academiaService.alterarAcademia(requestAcademia);
        return ResponseEntity.ok(responseAcademiaAlterar);
    }
    @DeleteMapping("/{idAcademia}")
    public ResponseEntity<ResponseAcademia> deletarAcademia(@PathVariable Long idAcademia) {
        ResponseAcademia responseAcademiaDeletar = academiaService.deletarAcademia(idAcademia);
        return ResponseEntity.ok(responseAcademiaDeletar);
    }
    }
