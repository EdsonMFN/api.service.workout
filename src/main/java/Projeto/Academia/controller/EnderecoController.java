package Projeto.Academia.controller;

import Projeto.Academia.service.EnderecoService;
import Projeto.Academia.service.request.RequestEndereco;
import Projeto.Academia.service.response.ResponseEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<ResponseEndereco> criarEndereco(@RequestBody RequestEndereco requestEndereco){

        ResponseEndereco responseEnderecoCriar = enderecoService.criarEndereco(requestEndereco);

        return ResponseEntity.ok(responseEnderecoCriar);
    }
}
