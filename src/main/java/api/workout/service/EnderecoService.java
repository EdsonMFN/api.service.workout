package api.workout.service;

import api.workout.domains.model.EnderecoDTO;
import api.workout.rest.request.RequestEndereco;
import api.workout.rest.response.ResponseEndereco;
import api.workout.domains.entitys.Endereco;
import api.workout.exception.handles.DataBindingViolationException;
import api.workout.exception.handles.ObjectNotFoundException;
import api.workout.domains.repositorys.RepositoryAcademia;
import api.workout.domains.repositorys.RepositoryEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private RepositoryAcademia repositoryAcademia;
    @Autowired
    private RepositoryEndereco repositoryEndereco;

    public ResponseEndereco criarEndereco(RequestEndereco requestEndereco){

        Endereco endereco = new Endereco(requestEndereco);
        repositoryEndereco.save(endereco);

        return new ResponseEndereco(new EnderecoDTO(endereco));
    }
    public List<ResponseEndereco> listarEndereco(){

        List<Endereco> enderecos = repositoryEndereco.findAll();
        List<ResponseEndereco> responseEnderecos = new ArrayList<>();

        enderecos.forEach(endereco -> {
            ResponseEndereco responseEndereco =
                    new ResponseEndereco(new EnderecoDTO(endereco));

            responseEnderecos.add(responseEndereco);
        });

        return responseEnderecos;
    }
    public ResponseEndereco buscarEndereco(Long idEndereco){
        Endereco endereco = repositoryEndereco.findById(idEndereco)
                .orElseThrow(() -> new ObjectNotFoundException("endereço com o ID "+idEndereco+" não encontrado."));

        return new ResponseEndereco(new EnderecoDTO(endereco));
    }
    public ResponseEndereco altararEndereco(RequestEndereco requestEndereco) {
        Endereco endereco = repositoryEndereco.getReferenceById(requestEndereco.getId());

        endereco.setCep(requestEndereco.getCep());
        endereco.setBairro(requestEndereco.getBairro());
        endereco.setNumero(requestEndereco.getNumero());
        endereco.setEstado(requestEndereco.getEstado());
        endereco.setCidade(requestEndereco.getCidade());
        repositoryEndereco.save(endereco);

        return new ResponseEndereco(new EnderecoDTO(endereco));
    }
    public ResponseEndereco deletarEndereco(Long idEndereco){
        Endereco endereco = repositoryEndereco.findById(idEndereco)
                .orElseThrow(() -> new DataBindingViolationException("endereço" + idEndereco +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryEndereco.delete(endereco);
        }catch (Exception e){
            throw new DataBindingViolationException("O endereco não pode ser deletado por precisar deletar entidades relacionas");
        }
        return new ResponseEndereco(new EnderecoDTO(endereco));
    }
}
