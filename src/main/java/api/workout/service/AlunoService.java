package api.workout.service;

import api.workout.builder.AcademiaDTOBuilder;
import api.workout.builder.AlunoDTOBuilder;
import api.workout.builder.PersonalDTOBuilder;
import api.workout.builder.ProfessorDTOBuilder;
import api.workout.rest.DTO.AcademiaDTO;
import api.workout.rest.DTO.EnderecoDTO;
import api.workout.rest.DTO.PersonalDTO;
import api.workout.rest.DTO.ProfessorDTO;
import api.workout.rest.request.RequestAluno;
import api.workout.rest.response.ResponseAluno;
import api.workout.entitys.academia.Academia;
import api.workout.entitys.aluno.Aluno;
import api.workout.entitys.personal.Personal;
import api.workout.entitys.professor.Professor;
import api.workout.exception.DataBindingViolationException;
import api.workout.exception.ObjectNotFoundException;

import api.workout.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private RepositoryAcademia repositoryAcademia;
    @Autowired
    private RepositoryProfessor repositoryProfessor;
    @Autowired
    private RepositoryPersonal repositoryPersonal;
    @Autowired
    private RepositoryAluno repositoryAluno;
    @Autowired
    private RepositoryFichaDeTreino repositoryFichaDeTreino;
    @Autowired
    private RepositoryEndereco repositoryEndereco;

    public ResponseAluno criarAluno(RequestAluno requestAluno){
        Academia academia = repositoryAcademia.getReferenceById(requestAluno.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestAluno.getIdProfessor());

        var endereco = academia.getEndereco();

        Aluno aluno = new Aluno();
        aluno.setNome(requestAluno.getNome());
        aluno.setCpf(requestAluno.getCpf());
        aluno.setAcademiaAfiliada(academia);
        aluno.setProfessor(professor);
        repositoryAluno.save(aluno);

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();

        return new ResponseAluno(AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build());

    }
    public List<ResponseAluno> listarAlunos(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("academia com o ID:" + idAcademia + " não encontrada."));

        var endereco = academia.getEndereco();

        List<Aluno> alunos = repositoryAluno.findByAcademiaAfiliada(academia);
        List<ResponseAluno> responseAlunos = new ArrayList<>();

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

        alunos.forEach(aluno -> {
            var professor = aluno.getProfessor();

            ProfessorDTO professorDTO = ProfessorDTOBuilder
                    .professorDTOBuilder()
                    .id(professor.getId())
                    .nome(professor.getNome())
                    .cpf(professor.getCpf())
                    .cref(professor.getCref())
                    .academiasAfiliada(academiaDTO)
                    .build();


            ResponseAluno responseAluno = new ResponseAluno(AlunoDTOBuilder
                    .alunoDTOBuilder()
                    .id(aluno.getId())
                    .cpf(aluno.getCpf())
                    .nome(aluno.getNome())
                    .idAcademiaAfiliada(academiaDTO)
                    .crefProfessor(professorDTO)
                    .build());

            responseAlunos.add(responseAluno);
        });
        return responseAlunos;
    }
    public ResponseAluno buscarAluno(String cpfAluno){
        Aluno aluno = repositoryAluno.findByCpf(cpfAluno).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + cpfAluno + " não encontrado."));

        var academia = aluno.getAcademiaAfiliada();
        var professor = aluno.getProfessor();
        var endereco = aluno.getAcademiaAfiliada().getEndereco();

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();

        return new ResponseAluno(AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build());
    }
    public ResponseAluno alterarAluno(RequestAluno requestAluno) {
        Aluno aluno = repositoryAluno.getReferenceByCpf(requestAluno.getCpf()).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + requestAluno.getCpf() + " não encontrado."));

        Academia academia = repositoryAcademia.getReferenceById(requestAluno.getIdAcademia());
        Personal personal = repositoryPersonal.getReferenceById(requestAluno.getIdPersonal());
        Professor professor = repositoryProfessor.getReferenceById(requestAluno.getIdPersonal());

        aluno.setNome(requestAluno.getNome());
        aluno.setAcademiaAfiliada(academia);
        aluno.setPersonal(personal);
        aluno.setProfessor(professor);
        repositoryAluno.save(aluno);

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .build();

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();

        PersonalDTO personalDTO = PersonalDTOBuilder
                .personalDTOBuilder()
                .id(personal.getId())
                .nome(personal.getNome())
                .cpf(personal.getCpf())
                .cref(personal.getCref())
                .build();

        return new ResponseAluno(AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefPersonal(personalDTO)
                .crefProfessor(professorDTO)
                .build());
    }
    public ResponseAluno deletarAluno(Long idAluno){
        Aluno aluno = repositoryAluno.findById(idAluno).map(a -> a)
                .orElseThrow(() -> new DataBindingViolationException("aluno de ID "+idAluno+"não pode ser deletado."));
        try {
            repositoryAluno.delete(aluno);
        }catch (Exception e){
            throw new DataBindingViolationException("O aluno não pode ser deletado por precisar deletar entidades relacionas");
        }
        return new ResponseAluno(AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .build());
    }
}
