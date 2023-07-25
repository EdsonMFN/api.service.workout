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

    @PostMapping("/{idAcademia}/{idProfessor}/{cpfAluno}")
    public ResponseEntity<ResponseFichaDeTreino> criarFicha(@PathVariable Long idAcademia, @PathVariable  Long idProfessor, @PathVariable String cpfAluno, @RequestBody RequestFichaDeTreino requestFichaDeTreino){

        ResponseFichaDeTreino responseFichaDeTreinoCriar = fichaDetreinoService.criarFicha(idAcademia, idProfessor, cpfAluno, requestFichaDeTreino);

        return ResponseEntity.ok(responseFichaDeTreinoCriar);
    }
    @GetMapping("/cpf/{cpfAluno}")
    public ResponseEntity<List<ResponseFichaDeTreino>> listarFichas(@PathVariable String cpfAluno){
        List<ResponseFichaDeTreino> responseFichaDeTreinos = fichaDetreinoService.listarFichas(cpfAluno);
        return ResponseEntity.ok(responseFichaDeTreinos);
    }
    @GetMapping("/{idFicha}")
    public ResponseEntity<ResponseFichaDeTreino> buscarFichas(@PathVariable Long idFicha){
            ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.buscarFicha(idFicha);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
    @PutMapping("/{idAcademia}/{cpfAluno}/{idProfessor}")
    public ResponseEntity<ResponseFichaDeTreino> alterarFicha(@PathVariable Long idAcademia,@PathVariable String cpfAluno, @PathVariable Long idProfessor, @RequestBody RequestFichaDeTreino requestFichaDeTreino){
        ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.alterarFicha(idAcademia,cpfAluno,idProfessor, requestFichaDeTreino);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
    @DeleteMapping("/{idFicha}")
    public ResponseEntity<ResponseFichaDeTreino> deletarFicha(@PathVariable Long idFicha){
        ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.deletarFicha(idFicha);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
}
