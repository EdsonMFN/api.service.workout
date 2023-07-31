package Projeto.Academia.service;

import Projeto.Academia.service.exception.ErrorException;
import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestAcademia;
import Projeto.Academia.controller.response.ResponseAcademia;
import Projeto.Academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademiaService {
    @Autowired
    private RepositoryAcademia repositoryAcademia;
    @Autowired
    private RepositoryProfessor respositoryProfessor;
    @Autowired
    private RepositoryPersonal repositoryPersonal;
    @Autowired
    private RepositoryAluno repositoryAluno;
    @Autowired
    private RepositoryFichaDeTreino repositoryFichaDeTreino;
    @Autowired
    private RepositoryEndereco repositoryEndereco;

    public ResponseAcademia criarAcademia(RequestAcademia requestAcademia){
        Endereco endereco = repositoryEndereco.getReferenceById(requestAcademia.getIdEndereco());

        Academia academia = new Academia();
        academia.setAcademiaAfiliada(requestAcademia.getAcademiaAfiliada());
        academia.setCnpj(requestAcademia.getCnpj());
        academia.setEndereco(endereco);
        repositoryAcademia.save(academia);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
    public List<ResponseAcademia> listarAcademia(){

        List<Academia> academias = repositoryAcademia.findAll();
        List<ResponseAcademia> responseAcademias = new ArrayList<>();


        for (Academia academia : academias){

            var endereco = academia.getEndereco();

            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(endereco.getId());
            enderecoDTO.setCep(endereco.getCep());
            enderecoDTO.setEstado(endereco.getEstado());
            enderecoDTO.setCidade(endereco.getCidade());
            enderecoDTO.setBairro(endereco.getBairro());
            enderecoDTO.setNumero(endereco.getNumero());

            AcademiaDTO academiaDTO = new AcademiaDTO();
            academiaDTO.setId(academia.getId());
            academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
            academiaDTO.setCnpj(academia.getCnpj());
            academiaDTO.setEndereco(enderecoDTO);

            ResponseAcademia responseAcademia = new ResponseAcademia();
            responseAcademia.setAcademiaDTO(academiaDTO);

            responseAcademias.add(responseAcademia);
        }

        return responseAcademias;
    }
    public ResponseAcademia buscarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("academia com o ID: " + idAcademia + " não encontrada."));

        var endereco = academia.getEndereco();

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
    public ResponseAcademia alterarAcademia(RequestAcademia requestAcademia){
        Academia academia = repositoryAcademia.getReferenceById(requestAcademia.getIdAcademia());
        Endereco endereco = repositoryEndereco.getReferenceById(requestAcademia.getIdEndereco());

        academia.setAcademiaAfiliada(requestAcademia.getAcademiaAfiliada());
        academia.setCnpj(requestAcademia.getCnpj());
        academia.setEndereco(endereco);
        repositoryAcademia.save(academia);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
    public ResponseAcademia deletarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ErrorException("academia não encontrada."));

        var endereco = academia.getEndereco();

        repositoryAcademia.delete(academia);


        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
}
