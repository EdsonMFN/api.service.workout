package api.workout.rest.controller;

import api.workout.rest.request.RequestUsuario;
import api.workout.rest.response.ResponseUsuario;
import api.workout.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<ResponseUsuario> login(@RequestBody RequestUsuario requestUsuario){
        ResponseUsuario responseUsuario = usuarioService.acessarLogin(requestUsuario);

        return ResponseEntity.ok(responseUsuario);
    }
    @PostMapping("/cadastro")
    public ResponseEntity<ResponseUsuario> cadastroLogin (@RequestBody RequestUsuario requestUsuario){
        ResponseUsuario responseUsuario = usuarioService.cadastroLogin(requestUsuario);
        return ResponseEntity.ok(responseUsuario);
    }
    @GetMapping
    public ResponseEntity<List<ResponseUsuario>> listarUsuarios (){
        List <ResponseUsuario> responseUsuario = usuarioService.listarUsuarios();
        return ResponseEntity.ok(responseUsuario);
    }
}
