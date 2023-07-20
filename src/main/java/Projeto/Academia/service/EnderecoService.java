package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryEndereco;
import Projeto.Academia.service.request.RequestEndereco;
import Projeto.Academia.service.response.ResponseEndereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
