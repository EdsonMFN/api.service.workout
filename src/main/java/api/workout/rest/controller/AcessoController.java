package api.workout.rest.controller;

import api.workout.client.FeignClient;
import api.workout.rest.request.RequestUsuario;
import api.workout.rest.response.ResponseUsuario;
import api.workout.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acesso")
@CrossOrigin(origins = "*")
public class AcessoController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FeignClient feignClient;

    @PostMapping("/cadastro")
    public ResponseEntity<ResponseUsuario> cadastroLogin (@RequestBody RequestUsuario requestUsuario,@RequestHeader("Authorization") String authorization){
        feignClient.autenticarUsuario(authorization);
        ResponseUsuario responseUsuario = usuarioService.cadastrarUsuario(requestUsuario,authorization);
        return ResponseEntity.ok(responseUsuario);
    }
    @GetMapping
    public ResponseEntity<List<ResponseUsuario>> listarUsuarios(@RequestHeader("Authorization") String authorization){
        feignClient.autenticarUsuario(authorization);
        List <ResponseUsuario> responseUsuario = usuarioService.listarUsuarios();
        return ResponseEntity.ok(responseUsuario);
    }

}
