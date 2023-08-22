package Projeto.Academia.security;

import Projeto.Academia.service.AutorizacaoService;
import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {
    @Autowired
    private FiltroDeSeguranca filtroDeSeguranca;
    @Autowired
    private AutorizacaoService autorizacaoService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return   http
                .csrf(csrf ->csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((autorizacao) -> autorizacao
                        
                        .requestMatchers(HttpMethod.POST,"/acesso/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/acesso/cadastro").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.GET,"/acesso").permitAll()

                        .requestMatchers(antMatcher("/endereco/**")).hasRole("GESTOR")

                        .requestMatchers(antMatcher("/academia/**")).hasRole("GESTOR")
                        .requestMatchers(HttpMethod.GET,"/academia/**").hasAnyRole("GESTOR","ALUNO")

                        .requestMatchers(antMatcher("/professor/**")).hasRole("GESTOR")

                        .requestMatchers(antMatcher("/aluno/**")).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/aluno/academia/**").hasAnyRole("ADMIN","PROFESSOR")
                        .requestMatchers(HttpMethod.GET,"/aluno/**").hasAnyRole("ADMIN","PROFESSOR")

                        .requestMatchers(antMatcher("/personal/**")).hasRole("ADMIN")

                        .requestMatchers(antMatcher("/fichaDeTreino/**")).hasRole("PROFESSOR")
                        .requestMatchers(HttpMethod.GET,"/fichaDeTreino").hasAnyRole("PROFESSOR","ALUNO")
                        .requestMatchers(HttpMethod.GET,"/fichaDeTreino/aluno").hasAnyRole("PROFESSOR","ALUNO")
                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(filtroDeSeguranca, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(autorizacaoService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.HEADERS;
    }
}
