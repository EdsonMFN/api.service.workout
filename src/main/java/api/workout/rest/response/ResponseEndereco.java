package api.workout.rest.response;

import api.workout.rest.DTO.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEndereco {

    private EnderecoDTO enderecoDTO;

    public ResponseEndereco(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
    }
}
