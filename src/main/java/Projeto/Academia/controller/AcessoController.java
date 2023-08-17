package Projeto.Academia.controller;

import Projeto.Academia.controller.request.RequestUsuario;
import Projeto.Academia.controller.response.ResponseUsuario;
import Projeto.Academia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
