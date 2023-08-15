package Projeto.Academia.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca {
    @Autowired
    private FiltroDeSeguranca filtroDeSeguranca;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return   http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorizacao -> autorizacao
                        .requestMatchers(HttpMethod.POST,"/acesso/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/acesso/cadastro").permitAll()
                        .requestMatchers(HttpMethod.POST,"/endereco").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,"/academia").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.POST,"/professor").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT,"/endereco").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT,"/academia").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.PUT,"/professor").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE,"/endereco").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE,"/academia").hasRole("GESTOR")
                        .requestMatchers(HttpMethod.DELETE,"/professor").hasRole("GESTOR")
                        .anyRequest().authenticated())
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
}
