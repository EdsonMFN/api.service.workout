package api.workout.service;


import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Endereco;
import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.EnderecoDTO;
import api.workout.domains.repositorys.*;
import api.workout.exception.handles.DataBindingViolationException;
import api.workout.exception.handles.ObjectNotFoundException;
import api.workout.rest.request.RequestAcademia;
import api.workout.rest.response.ResponseAcademia;
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
        Endereco endereco = repositoryEndereco.findById(requestAcademia.getEndereco().getId())
                .orElseThrow(() -> new ObjectNotFoundException("academia com o ID: " + requestAcademia.getEndereco().getId() + " não encontrada."));

        Academia academia = new Academia(requestAcademia);
        academia.setEndereco(endereco);
        repositoryAcademia.save(academia);

        return new ResponseAcademia(new AcademiaDTO(academia));
    }
    public List<ResponseAcademia> listarAcademia(){

        List<Academia> academias = repositoryAcademia.findAll();
        List<ResponseAcademia> responseAcademias = new ArrayList<>();

        academias.forEach(academia -> {
            ResponseAcademia responseAcademia = new ResponseAcademia(new AcademiaDTO(academia));

            responseAcademias.add(responseAcademia);
        });

        return responseAcademias;
    }
    public ResponseAcademia buscarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia)
                .orElseThrow(() -> new ObjectNotFoundException("academia com o ID: " + idAcademia + " não encontrada."));

        var endereco = academia.getEndereco();

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        return new ResponseAcademia(new AcademiaDTO(academia));
    }
    public ResponseAcademia alterarAcademia(RequestAcademia requestAcademia){
        Academia academia = repositoryAcademia.getReferenceById(requestAcademia.getId());
        Endereco endereco = repositoryEndereco.getReferenceById(requestAcademia.getId());

        academia.setAcademiaAfiliada(requestAcademia.getAcademiaAfiliada());
        academia.setCnpj(requestAcademia.getCnpj());
        academia.setEndereco(endereco);
        repositoryAcademia.save(academia);

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        return new ResponseAcademia(new AcademiaDTO(academia));
    }
    public ResponseAcademia deletarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia)
                .orElseThrow(() -> new DataBindingViolationException("academia" + idAcademia +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryAcademia.delete(academia);
        }catch (Exception e){
            throw new DataBindingViolationException("A academia não pode ser deletado por precisar deletar entidades relacionas");
        }
        var endereco = academia.getEndereco();

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        return new ResponseAcademia(new AcademiaDTO(academia));
    }
}
