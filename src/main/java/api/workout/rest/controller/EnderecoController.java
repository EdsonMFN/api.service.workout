package api.workout.rest.controller;

import api.workout.rest.request.RequestEndereco;
import api.workout.rest.response.ResponseEndereco;
import api.workout.service.EnderecoService;
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
    @PutMapping
    public ResponseEntity<ResponseEndereco> alterarEndereco(@RequestBody RequestEndereco requestEndereco){
        ResponseEndereco responseEnderecoAlterar = enderecoService.altararEndereco(requestEndereco);
        return ResponseEntity.ok(responseEnderecoAlterar);
    }
    @DeleteMapping("/{idEndereco}")
    public ResponseEntity<ResponseEndereco> deletarEndereco(@PathVariable Long idEndereco){
        ResponseEndereco responseEnderecosDeletar = enderecoService.deletarEndereco(idEndereco);
        return ResponseEntity.ok(responseEnderecosDeletar);

    }
}
