package api.workout.service;

import api.workout.builder.AcademiaDTOBuilder;
import api.workout.rest.DTO.EnderecoDTO;
import api.workout.rest.request.RequestAcademia;
import api.workout.rest.response.ResponseAcademia;
import api.workout.entitys.academia.Academia;
import api.workout.entitys.endereco.Endereco;
import api.workout.exception.DataBindingViolationException;
import api.workout.exception.ObjectNotFoundException;
import api.workout.repositorys.*;
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

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        return new ResponseAcademia(AcademiaDTOBuilder.academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build());
    }
    public List<ResponseAcademia> listarAcademia(){

        List<Academia> academias = repositoryAcademia.findAll();
        List<ResponseAcademia> responseAcademias = new ArrayList<>();

        academias.parallelStream().forEach(academia -> {
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


            ResponseAcademia responseAcademia = new ResponseAcademia(AcademiaDTOBuilder
                    .academiaDTOBuilder()
                    .id(academia.getId())
                    .academiaAfiliada(academia.getAcademiaAfiliada())
                    .cnpj(academia.getCnpj())
                    .endereco(enderecoDTO)
                    .build());

            responseAcademias.add(responseAcademia);
        });

        return responseAcademias;
    }
    public ResponseAcademia buscarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
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

        return new ResponseAcademia(AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build());
    }
    public ResponseAcademia alterarAcademia(RequestAcademia requestAcademia){
        Academia academia = repositoryAcademia.getReferenceById(requestAcademia.getIdAcademia());
        Endereco endereco = repositoryEndereco.getReferenceById(requestAcademia.getIdEndereco());

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

        return new ResponseAcademia(AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build());
    }
    public ResponseAcademia deletarAcademia(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
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

        return new ResponseAcademia(AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build());
    }
}
