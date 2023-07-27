package Projeto.Academia.service;

import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryEndereco;
import Projeto.Academia.controller.request.RequestEndereco;
import Projeto.Academia.controller.response.ResponseEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        ResponseEndereco responseEndereco = new ResponseEndereco();
        responseEndereco.setEnderecoDTO(enderecoDTO);

        return responseEndereco;
    }
    public List<ResponseEndereco> listarEndereco(){

        List<Endereco> enderecos = repositoryEndereco.findAll();
        List<ResponseEndereco> responseEnderecos = new ArrayList<>();

        for (Endereco endereco : enderecos){
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(endereco.getId());
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setEstado(endereco.getEstado());
            enderecoDTO.setNumero(endereco.getNumero());

            ResponseEndereco responseEndereco = new ResponseEndereco();
            responseEndereco.setEnderecoDTO(enderecoDTO);

            responseEnderecos.add(responseEndereco);
        }
        return responseEnderecos;
    }
    public ResponseEndereco buscarEndereco(Long idEndereco){
        Optional<Endereco> enderecoOptional = repositoryEndereco.findById(idEndereco);

        var endereco = enderecoOptional.get();

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        ResponseEndereco responseEndereco = new ResponseEndereco();
        responseEndereco.setEnderecoDTO(enderecoDTO);

        return responseEndereco;
    }
    public ResponseEndereco altararEndereco(RequestEndereco requestEndereco){
        Endereco endereco = repositoryEndereco.getReferenceById(requestEndereco.getIdEndereco());

        endereco.setCep(requestEndereco.getCep());
        endereco.setBairro(requestEndereco.getBairro());
        endereco.setNumero(requestEndereco.getNumero());
        endereco.setEstado(requestEndereco.getEstado());
        endereco.setCidade(requestEndereco.getCidade());
        repositoryEndereco.save(endereco);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        ResponseEndereco responseEndereco = new ResponseEndereco();
        responseEndereco.setEnderecoDTO(enderecoDTO);

        return responseEndereco;
    }
    public ResponseEndereco deletarEndereco(Long idEndereco){
        Optional<Endereco> enderecoOptional = repositoryEndereco.findById(idEndereco);

        var endereco = enderecoOptional.get();

        repositoryEndereco.delete(endereco);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        ResponseEndereco responseEndereco = new ResponseEndereco();
        responseEndereco.setEnderecoDTO(enderecoDTO);

        return responseEndereco;

    }
}
