package api.workout.rest.controller;

import api.workout.rest.request.RequestBaixarTreino;
import api.workout.rest.request.RequestFichaDeTreino;
import api.workout.rest.response.ResponseArquivoFichaTreino;
import api.workout.rest.response.ResponseFichaDeTreino;
import api.workout.service.FichaDetreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @PostMapping("/arquivoFichaTreino")
    public ResponseEntity<ResponseArquivoFichaTreino> criarArquivoFichaDeTreino(@RequestBody RequestBaixarTreino baixarTreino){
        ResponseArquivoFichaTreino responseArquivoFichaTreino = fichaDetreinoService.criarAquivoTreino(baixarTreino);
        return ResponseEntity.ok(responseArquivoFichaTreino);
    }
    @GetMapping("/aluno/{cpfAluno}")
    public ResponseEntity<List<ResponseFichaDeTreino>> listarFichas(@PathVariable String cpfAluno){
        List<ResponseFichaDeTreino> responseFichaDeTreinos = fichaDetreinoService.listarFichas(cpfAluno);
        return ResponseEntity.ok(responseFichaDeTreinos);
    }
    @GetMapping("/{idFicha}")
    @Cacheable(value = "fichaDeTreino")
    public ResponseEntity<ResponseFichaDeTreino> buscarFichas(@PathVariable Long idFicha){
            ResponseFichaDeTreino responseFichaDeTreino = fichaDetreinoService.buscarFicha(idFicha);
        return ResponseEntity.ok(responseFichaDeTreino);
    }
    @PutMapping
    @CachePut(value = "fichaDeTreino")
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
