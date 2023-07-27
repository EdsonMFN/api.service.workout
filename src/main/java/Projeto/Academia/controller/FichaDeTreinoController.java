package Projeto.Academia.controller;

import Projeto.Academia.service.FichaDetreinoService;
import Projeto.Academia.controller.request.RequestFichaDeTreino;
import Projeto.Academia.controller.response.ResponseFichaDeTreino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichaDeTreino")
public class FichaDeTreinoController {

    @Autowired
    private FichaDetreinoService fichaDetreinoService;

    @PostMapping
    public ResponseEntity<ResponseFichaDeTreino> criarFicha(@RequestBody RequestFichaDeTreino requestFichaDeTreino){

        ResponseFichaDeTreino responseFichaDeTreinoCriar = fichaDetreinoService.criarFicha(requestFichaDeTreino);

        return ResponseEntity.ok(responseFichaDeTreinoCriar);
    }
    @GetMapping("/aluno/{cpfAluno}")
    public ResponseEntity<List<ResponseFichaDeTreino>> listarFichas(@PathVariable String cpfAluno){
        List<ResponseFichaDeTreino> responseFichaDeTreinos = fichaDetreinoService.listarFichas(cpfAluno);
        return ResponseEntity.ok(responseFichaDeTreinos);
    }
    @GetMapping("/{idFicha}")
    public ResponseEntity<ResponseFichaDeTreino> buscarFichas(@PathVariable Long idFicha){
            ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.buscarFicha(idFicha);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
    @PutMapping
    public ResponseEntity<ResponseFichaDeTreino> alterarFicha(@RequestBody RequestFichaDeTreino requestFichaDeTreino){
        ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.alterarFicha(requestFichaDeTreino);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
    @DeleteMapping("/{idFicha}")
    public ResponseEntity<ResponseFichaDeTreino> deletarFicha(@PathVariable Long idFicha){
        ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.deletarFicha(idFicha);
        return ResponseEntity.ok(responseFichaDeTreino);

    }
}
