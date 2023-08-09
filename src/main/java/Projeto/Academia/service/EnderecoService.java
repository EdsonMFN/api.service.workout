package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.controller.request.RequestEndereco;
import Projeto.Academia.controller.response.ResponseEndereco;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.exception.DataBindingViolationException;
import Projeto.Academia.exception.ObjectNotFoundException;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryEndereco;
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

        Endereco endereco = new Endereco();
        endereco.setCep(requestEndereco.getCep());
        endereco.setBairro(requestEndereco.getBairro());
        endereco.setNumero(requestEndereco.getNumero());
        endereco.setEstado(requestEndereco.getEstado());
        endereco.setCidade(requestEndereco.getCidade());
        repositoryEndereco.save(endereco);

        return new ResponseEndereco(EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build());
    }
    public List<ResponseEndereco> listarEndereco(){

        List<Endereco> enderecos = repositoryEndereco.findAll();
        List<ResponseEndereco> responseEnderecos = new ArrayList<>();

        enderecos.parallelStream().forEach(endereco -> {
            ResponseEndereco responseEndereco =
                    new ResponseEndereco(EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build());

            responseEnderecos.add(responseEndereco);
        });

        return responseEnderecos;
    }
    public ResponseEndereco buscarEndereco(Long idEndereco){
        Endereco endereco = repositoryEndereco.findById(idEndereco).map(e -> e)
                .orElseThrow(() -> new ObjectNotFoundException("endereço com o ID "+idEndereco+" não encontrado."));

        return new ResponseEndereco(EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build());
    }
    public ResponseEndereco altararEndereco(RequestEndereco requestEndereco) {
        Endereco endereco = repositoryEndereco.getReferenceById(requestEndereco.getIdEndereco());

        endereco.setCep(requestEndereco.getCep());
        endereco.setBairro(requestEndereco.getBairro());
        endereco.setNumero(requestEndereco.getNumero());
        endereco.setEstado(requestEndereco.getEstado());
        endereco.setCidade(requestEndereco.getCidade());
        repositoryEndereco.save(endereco);

        return new ResponseEndereco(EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build());
    }
    public ResponseEndereco deletarEndereco(Long idEndereco){
        Endereco endereco = repositoryEndereco.findById(idEndereco).map(e -> e)
                .orElseThrow(() -> new DataBindingViolationException("endereço" + idEndereco +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryEndereco.delete(endereco);
        }catch (Exception e){
            throw new DataBindingViolationException("O endereco não pode ser deletado por precisar deletar entidades relacionas");
        }


        return new ResponseEndereco(EnderecoDTO
                    .builder()
                    .id(endereco.getId())
                    .cep(endereco.getCep())
                    .estado(endereco.getEstado())
                    .bairro(endereco.getBairro())
                    .cidade(endereco.getCidade())
                    .numero(endereco.getNumero())
                    .build());
    }
}
