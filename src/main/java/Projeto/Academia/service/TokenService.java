package Projeto.Academia.service;

import Projeto.Academia.entitys.acesso.Usuario;
import Projeto.Academia.repositorys.RepositoryUsuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Value("${api.security.token.secret}")
    private  String secret;

    public String gerarToken(Usuario usuario){

        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("academia_Api")
                    .withSubject(usuario.getNomeUsuario())
                    .withClaim("Role", String.valueOf(usuario.getRole()))
                    .withExpiresAt(tempoDeUsoToken())
                    .sign(algoritimo);
        }catch (JWTCreationException ex){
            throw new RuntimeException("erro ao gerar o token", ex);
        }
    }
    public String validacaoToken(String token){

        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.require(algoritimo)
                    .withIssuer("academia_Api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException ex){
           return "Token invalido ou expirado";
        }
    }
    public String gerarRefreshToken(Usuario usuario){

        try {
            Algorithm algoritimo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("academia_Api")
                    .withExpiresAt(tempoDeUsoRefreshToken())
                    .withSubject(usuario.getNomeUsuario())
                    .sign(algoritimo);
        }catch (JWTCreationException ex){
            throw new RuntimeException("erro ao gerar o token", ex);
        }
    }
    private Instant tempoDeUsoToken(){

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
    private Instant tempoDeUsoRefreshToken(){

        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
