package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.endereco.Endereco;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.service.request.RequestAcademia;
import Projeto.Academia.service.response.ResponseAcademia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
