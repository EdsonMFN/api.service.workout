package Projeto.Academia.controller;

import Projeto.Academia.service.PersonalService;
import Projeto.Academia.service.request.RequestPersonal;
import Projeto.Academia.service.response.ResponsePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @PostMapping("/{idAcademia}/{cpfAluno}")
    public ResponseEntity<ResponsePersonal> criarAluno(@PathVariable Long idAcademia, @PathVariable String cpfAluno,@RequestBody RequestPersonal requestPersonal){

        ResponsePersonal responsePersonalCriar = personalService.criarPersonal(idAcademia,cpfAluno,requestPersonal);

        return ResponseEntity.ok(responsePersonalCriar);
    }
}
