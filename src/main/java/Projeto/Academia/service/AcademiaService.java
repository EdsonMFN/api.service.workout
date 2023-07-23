package Projeto.Academia.service;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestAcademia;
import Projeto.Academia.controller.response.ResponseAcademia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseAcademia criarAcademia(Long idEndereco,RequestAcademia requestAcademia){
        Optional<Endereco> endereco = repositoryEndereco.findById(idEndereco);

        Academia academia = new Academia();
        academia.setAcademiaAfiliada(requestAcademia.getAcademiaAfiliada());
        academia.setCnpj(requestAcademia.getCnpj());
        academia.setEndereco(endereco.get());
        repositoryAcademia.save(academia);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.get().getId());
        enderecoDTO.setCep(endereco.get().getCep());
        enderecoDTO.setBairro(endereco.get().getBairro());
        enderecoDTO.setCidade(endereco.get().getCidade());
        enderecoDTO.setEstado(endereco.get().getEstado());
        enderecoDTO.setNumero(endereco.get().getNumero());

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

            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(academia.getEndereco().getId());
            enderecoDTO.setCep(academia.getEndereco().getCep());
            enderecoDTO.setEstado(academia.getEndereco().getEstado());
            enderecoDTO.setCidade(academia.getEndereco().getCidade());
            enderecoDTO.setBairro(academia.getEndereco().getBairro());
            enderecoDTO.setNumero(academia.getEndereco().getNumero());

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
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(academia.get().getEndereco().getId());
        enderecoDTO.setCep(academia.get().getEndereco().getCep());
        enderecoDTO.setEstado(academia.get().getEndereco().getEstado());
        enderecoDTO.setCidade(academia.get().getEndereco().getCidade());
        enderecoDTO.setBairro(academia.get().getEndereco().getBairro());
        enderecoDTO.setNumero(academia.get().getEndereco().getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.get().getId());
        academiaDTO.setAcademiaAfiliada(academia.get().getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.get().getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
    public ResponseAcademia alterarAcademia(RequestAcademia requestAcademia){
        Academia academia = repositoryAcademia.getReferenceById(requestAcademia.getIdAcademia());

        academia.setAcademiaAfiliada(requestAcademia.getAcademiaAfiliada());
        academia.setCnpj(requestAcademia.getCnpj());
        repositoryAcademia.save(academia);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(academia.getEndereco().getId());
        enderecoDTO.setCep(academia.getEndereco().getCep());
        enderecoDTO.setEstado(academia.getEndereco().getEstado());
        enderecoDTO.setCidade(academia.getEndereco().getCidade());
        enderecoDTO.setBairro(academia.getEndereco().getBairro());
        enderecoDTO.setNumero(academia.getEndereco().getNumero());

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
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        repositoryAcademia.delete(academia.get());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(academia.get().getEndereco().getId());
        enderecoDTO.setCep(academia.get().getEndereco().getCep());
        enderecoDTO.setEstado(academia.get().getEndereco().getEstado());
        enderecoDTO.setCidade(academia.get().getEndereco().getCidade());
        enderecoDTO.setBairro(academia.get().getEndereco().getBairro());
        enderecoDTO.setNumero(academia.get().getEndereco().getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.get().getId());
        academiaDTO.setAcademiaAfiliada(academia.get().getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.get().getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ResponseAcademia responseAcademia = new ResponseAcademia();
        responseAcademia.setAcademiaDTO(academiaDTO);

        return responseAcademia;
    }
}
