package Projeto.Academia.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "Beer", url = "https://api.punkapi.com/v2/beers ")
public interface FeignClient {

    @GetMapping(value = "/{id}")
    List<BearResponse> getBearResponse (@PathVariable Long id);

}
