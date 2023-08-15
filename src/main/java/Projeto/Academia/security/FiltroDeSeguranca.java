package Projeto.Academia.security;

import Projeto.Academia.repositorys.RepositoryUsuario;
import Projeto.Academia.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = this.limpesaToken(request);
        if (token != null){
            var login = tokenService.validacaoToken(token);
            UserDetails usuario = repositoryUsuario.findByNomeUsuario(login);

            var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }
        filterChain.doFilter(request,response);
    }

    private String limpesaToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null) return null;
        return authorizationHeader.replace("Bearer ", "");
    }
}
