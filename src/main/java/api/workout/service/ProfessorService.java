package api.workout.service;

import api.workout.builder.AcademiaDTOBuilder;
import api.workout.builder.ProfessorDTOBuilder;
import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.EnderecoDTO;
import api.workout.domains.model.ProfessorDTO;
import api.workout.rest.request.RequestProfessor;
import api.workout.rest.response.ResponseProfessor;
import api.workout.domains.entitys.academia.Academia;
import api.workout.domains.entitys.professor.Professor;
import api.workout.exception.DataBindingViolationException;
import api.workout.exception.ErrorException;
import api.workout.exception.ObjectNotFoundException;
import api.workout.domains.repositorys.RepositoryAcademia;
import api.workout.domains.repositorys.RepositoryAluno;
import api.workout.domains.repositorys.RepositoryFichaDeTreino;
import api.workout.domains.repositorys.RepositoryProfessor;
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

        Professor professor = new Professor();
        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        professor.setAcademiaAfiliada(academia);
        repositoryProfessor.save(professor);

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        return new ResponseProfessor(ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build());
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

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        List<Professor> professores = repositoryProfessor.findAllByAcademiaAfiliada(academia);
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        List<ResponseProfessor> responseProfessores = new ArrayList<>();

        professores.forEach(professor -> {
            ProfessorDTO professorDTO = ProfessorDTOBuilder
                    .professorDTOBuilder()
                    .id(professor.getId())
                    .nome(professor.getNome())
                    .cpf(professor.getCpf())
                    .cref(professor.getCref())
                    .academiasAfiliada(academiaDTO)
                    .build();

            academiaDTO.setProfessores(professorDTOS);
            academiaDTO.getProfessores().add(professorDTO);

            ResponseProfessor responseProfessor = new ResponseProfessor(professorDTO);
            responseProfessores.add(responseProfessor);
        });
        return responseProfessores;
    }
    public ResponseProfessor buscarProfessor(Long idProfessor){
        Professor professor = repositoryProfessor.findById(idProfessor).map(p->p)
                .orElseThrow(()-> new ErrorException("professor não encontrado"));

        var academia = professor.getAcademiaAfiliada();
        var endereco = professor.getAcademiaAfiliada().getEndereco();

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        return new ResponseProfessor(ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build());
    }
    public ResponseProfessor alterarProfessor(RequestProfessor requestProfessor){
        Professor professor = repositoryProfessor.getReferenceById(requestProfessor.getIdProfessor());
        Academia academia = repositoryAcademia.getReferenceById(requestProfessor.getIdAcademia());

        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        professor.setAcademiaAfiliada(academia);
        repositoryProfessor.save(professor);

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .build();

        return new ResponseProfessor(ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build());
    }
    public ResponseProfessor deletarProfessor(Long idProfessor){
        Professor professor = repositoryProfessor.findById(idProfessor).map(p->p)
                .orElseThrow(()-> new DataBindingViolationException("professor " + idProfessor +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryProfessor.deleteById(idProfessor);
        }catch (Exception e){
            throw new DataBindingViolationException("O professor não pode ser deletado, por precisar deletar entidades relacionas");
        }


        var academia = professor.getAcademiaAfiliada();
        var endereco = professor.getAcademiaAfiliada().getEndereco();

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        return new ResponseProfessor(ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build());
    }
}
