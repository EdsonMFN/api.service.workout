package Projeto.Academia.controller;

import Projeto.Academia.service.EnderecoService;
import Projeto.Academia.controller.request.RequestEndereco;
import Projeto.Academia.controller.response.ResponseEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<ResponseEndereco>> listarEndereco(){
        List<ResponseEndereco> responseEnderecos = enderecoService.listarEndereco();
        return ResponseEntity.ok(responseEnderecos);
    }
    @GetMapping("/{idEndereco}")
    public ResponseEntity<ResponseEndereco> buscarEndereco(@PathVariable Long idEndereco){
            ResponseEndereco responseEnderecosBuscar = enderecoService.buscarEndereco(idEndereco);
        return ResponseEntity.ok(responseEnderecosBuscar);
    }

}
