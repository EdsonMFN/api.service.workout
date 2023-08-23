package api.workout.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "Auth", url = "http://localhost:8080/auth")
public interface FeignClient {

    @GetMapping(value = "/{id}")
    List<Usuario> getBearResponse (@PathVariable Long id);

}
