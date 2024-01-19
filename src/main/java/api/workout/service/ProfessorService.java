package api.workout.service;

import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Professor;
import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.EnderecoDTO;
import api.workout.domains.model.ProfessorDTO;
import api.workout.domains.repositorys.RepositoryAcademia;
import api.workout.domains.repositorys.RepositoryAluno;
import api.workout.domains.repositorys.RepositoryFichaDeTreino;
import api.workout.domains.repositorys.RepositoryProfessor;
import api.workout.exception.handles.DataBindingViolationException;
import api.workout.exception.handles.ErrorException;
import api.workout.exception.handles.ObjectNotFoundException;
import api.workout.rest.request.RequestProfessor;
import api.workout.rest.response.ResponseProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private RepositoryAcademia repositoryAcademia;
    @Autowired
    private RepositoryProfessor repositoryProfessor;
    @Autowired
    private RepositoryAluno repositoryAluno;
    @Autowired
    private RepositoryFichaDeTreino repositoryFichaDeTreino;

    public ResponseProfessor criarProfessor(RequestProfessor requestProfessor){
        Academia academia = repositoryAcademia.getReferenceById(requestProfessor.getIdAcademia());

        var endereco = academia.getEndereco();

        Professor professor = new Professor(requestProfessor);
        professor.setAcademiaAfiliada(academia);
        repositoryProfessor.save(professor);

//        EnderecoDTO enderecoDTO = EnderecoDTO
//                .builder()
//                .id(endereco.getId())
//                .cep(endereco.getCep())
//                .estado(endereco.getEstado())
//                .bairro(endereco.getBairro())
//                .cidade(endereco.getCidade())
//                .numero(endereco.getNumero())
//                .build();
//
//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .endereco(enderecoDTO)
//                .build();

        return new ResponseProfessor(new ProfessorDTO(professor));
    }
    public List<ResponseProfessor> listarProfessor(Long idAcademia){
        Academia academia= repositoryAcademia.findById(idAcademia)
                .orElseThrow(()-> new ObjectNotFoundException("academia com o ID: " + idAcademia + " não encontrada."));

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

        AcademiaDTO academiaDTO = AcademiaDTO.builder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();


        List<Professor> professores = repositoryProfessor.findAllByAcademiaAfiliada(academia);
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        List<ResponseProfessor> responseProfessores = new ArrayList<>();

        professores.forEach(professor -> {
            ProfessorDTO professorDTO = new ProfessorDTO(professor);

            academiaDTO.setProfessores(professorDTOS);
            academiaDTO.getProfessores().add(professorDTO);

            ResponseProfessor responseProfessor = new ResponseProfessor(professorDTO);
            responseProfessores.add(responseProfessor);
        });
        return responseProfessores;
    }
    public ResponseProfessor buscarProfessor(Long idProfessor){
        Professor professor = repositoryProfessor.findById(idProfessor)
                .orElseThrow(()-> new ErrorException("professor não encontrado"));

//        var academia = professor.getAcademiaAfiliada();
//        var endereco = professor.getAcademiaAfiliada().getEndereco();
//
//        EnderecoDTO enderecoDTO = EnderecoDTO
//                .builder()
//                .id(endereco.getId())
//                .cep(endereco.getCep())
//                .estado(endereco.getEstado())
//                .bairro(endereco.getBairro())
//                .cidade(endereco.getCidade())
//                .numero(endereco.getNumero())
//                .build();
//
//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .endereco(enderecoDTO)
//                .build();

        return new ResponseProfessor(new ProfessorDTO(professor));
    }
    public ResponseProfessor alterarProfessor(RequestProfessor requestProfessor){
        Professor professor = repositoryProfessor.getReferenceById(requestProfessor.getId());
        Academia academia = repositoryAcademia.getReferenceById(requestProfessor.getIdAcademia());

        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        professor.setAcademiaAfiliada(academia);
        repositoryProfessor.save(professor);

//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .build();

        return new ResponseProfessor(new ProfessorDTO(professor));
    }
    public ResponseProfessor deletarProfessor(Long idProfessor){
        Professor professor = repositoryProfessor.findById(idProfessor)
                .orElseThrow(()-> new DataBindingViolationException("professor " + idProfessor +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryProfessor.deleteById(idProfessor);
//
//        var academia = professor.getAcademiaAfiliada();
//        var endereco = professor.getAcademiaAfiliada().getEndereco();
//
//        EnderecoDTO enderecoDTO = EnderecoDTO
//                .builder()
//                .id(endereco.getId())
//                .cep(endereco.getCep())
//                .estado(endereco.getEstado())
//                .bairro(endereco.getBairro())
//                .cidade(endereco.getCidade())
//                .numero(endereco.getNumero())
//                .build();
//
//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .endereco(enderecoDTO)
//                .build();

        return new ResponseProfessor(new ProfessorDTO(professor));
        }catch (Exception e){
            throw new DataBindingViolationException("O professor não pode ser deletado, por precisar deletar entidades relacionas");
        }
    }
}
