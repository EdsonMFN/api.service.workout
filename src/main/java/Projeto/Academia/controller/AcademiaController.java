package Projeto.Academia.controller;

import Projeto.Academia.service.AcademiaService;
import Projeto.Academia.service.request.RequestAcademia;
import Projeto.Academia.service.response.ResponseAcademia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/academia")
public class AcademiaController {

    @Autowired
    private AcademiaService academiaService;

    @PostMapping("/{idEndereco}")
    public ResponseEntity<ResponseAcademia> criarAcademia(@PathVariable Long idEndereco, @RequestBody RequestAcademia requestAcademia){
        ResponseAcademia responseAcademiaCriar = academiaService.criarAcademia(idEndereco,requestAcademia);
        return ResponseEntity.ok(responseAcademiaCriar);
    }
}
