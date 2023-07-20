package Projeto.Academia.controller;

import Projeto.Academia.service.FichaDetreinoService;
import Projeto.Academia.controller.request.RequestFichaDeTreino;
import Projeto.Academia.controller.response.ResponseFichaDeTreino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
