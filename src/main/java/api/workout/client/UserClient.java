package api.workout.client;

import api.workout.rest.response.ResponseUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@org.springframework.cloud.openfeign.FeignClient(name = "Auth", url = "http://localhost:8090/acesso")
public interface UserClient {

    @GetMapping("/authUsuario")
    ResponseEntity<ResponseUsuario> autenticarUsuario(@RequestHeader("Authorization") String authorization);

}
