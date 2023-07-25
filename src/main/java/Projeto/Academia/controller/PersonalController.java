package Projeto.Academia.controller;

import Projeto.Academia.service.PersonalService;
import Projeto.Academia.controller.request.RequestPersonal;
import Projeto.Academia.controller.response.ResponsePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @PostMapping("/{cpfAluno}/{idAcademia}")
    public ResponseEntity<ResponsePersonal> criarAluno(@PathVariable Long idAcademia, @PathVariable String cpfAluno,@RequestBody RequestPersonal requestPersonal){

        ResponsePersonal responsePersonalCriar = personalService.criarPersonal(idAcademia,cpfAluno,requestPersonal);

        return ResponseEntity.ok(responsePersonalCriar);
    }
    @GetMapping("/academia/{idAcademia}")
    public ResponseEntity<List<ResponsePersonal>> listarPersonal(@PathVariable Long idAcademia){
        List<ResponsePersonal> responsePersonalListar = personalService.listarPersonal(idAcademia);
        return ResponseEntity.ok(responsePersonalListar);
    }
    @GetMapping("/{idPersonal}")
    public ResponseEntity<ResponsePersonal> buscarPersonal(@PathVariable Long idPersonal){
         ResponsePersonal responsePersonalBuscar = personalService.buscarPersonal(idPersonal);
        return ResponseEntity.ok(responsePersonalBuscar);
    }
    @PutMapping("/{idAcademia}")
    public ResponseEntity<ResponsePersonal> alterarPersonal(@PathVariable Long idAcademia,@RequestBody RequestPersonal requestPersonal){
        ResponsePersonal responsePersonalAlterar = personalService.alterarPersonal(idAcademia,requestPersonal);
        return ResponseEntity.ok(responsePersonalAlterar);
    }
    @DeleteMapping("/{idPersonal}")
    public ResponseEntity<ResponsePersonal> deletarPersonal(@PathVariable Long idPersonal){
        ResponsePersonal responsePersonalDeletar = personalService.deletarPersoanl(idPersonal);
        return ResponseEntity.ok(responsePersonalDeletar);
    }
}
