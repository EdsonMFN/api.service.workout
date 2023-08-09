package Projeto.Academia.service;

import Projeto.Academia.builder.AcademiaDTOBuilder;
import Projeto.Academia.builder.AlunoDTOBuilder;
import Projeto.Academia.builder.FichaDeTreinoDTOBuilder;
import Projeto.Academia.builder.ProfessorDTOBuilder;
import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.AlunoDTO;
import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.controller.DTO.ProfessorDTO;
import Projeto.Academia.controller.request.RequestFichaDeTreino;
import Projeto.Academia.controller.response.ResponseFichaDeTreino;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.exception.DataBindingViolationException;
import Projeto.Academia.exception.ErrorException;
import Projeto.Academia.exception.ObjectNotFoundException;
import Projeto.Academia.repositorys.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FichaDetreinoService {
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

    public ResponseFichaDeTreino criarFicha(RequestFichaDeTreino requestFichaDeTreino){
        Academia academia = repositoryAcademia.getReferenceById(requestFichaDeTreino.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestFichaDeTreino.getIdProfessor());

        Aluno aluno = repositoryAluno.getReferenceByCpf(requestFichaDeTreino.getCpfAluno()).map(a -> a)
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        FichaDeTreino fichaDeTreino = new FichaDeTreino();
        fichaDeTreino.setAcademiaAfiliada(academia);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAluno(aluno);
        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        repositoryFichaDeTreino.save(fichaDeTreino);

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public List<ResponseFichaDeTreino> listarFichas(String cpfAluno){
        Aluno aluno = repositoryAluno.findByCpf(cpfAluno).map(a -> a)
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

        var professor = aluno.getProfessor();
        var academia = aluno.getAcademiaAfiliada();
        var endereco = aluno.getAcademiaAfiliada().getEndereco();

        List<FichaDeTreino> fichaDeTreinos = repositoryFichaDeTreino.findByAluno(aluno);
        List<ResponseFichaDeTreino> responseFichaDeTreinos = new ArrayList<>();

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


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        fichaDeTreinos.parallelStream().forEach(fichaDeTreino -> {
            ResponseFichaDeTreino responseFichaDeTreino =
                    new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                    .fichaDeTreinoDTOBuilder()
                    .id(fichaDeTreino.getId())
                    .academiaAfiliada(academiaDTO)
                    .professor(professorDTO)
                    .aluno(alunoDTO)
                    .exercicio(fichaDeTreino.getExercicio())
                    .build());

            responseFichaDeTreinos.add(responseFichaDeTreino);
        });
        return responseFichaDeTreinos;
    }
    public ResponseFichaDeTreino buscarFicha(Long idFicha){
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha).map(f -> f)
                .orElseThrow(() -> new ObjectNotFoundException("Ficha com o ID"+idFicha+" não encontrada."));

        var academia = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();
        var endereco = academia.getEndereco();
        var professor = aluno.getProfessor();

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


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public ResponseFichaDeTreino alterarFicha (RequestFichaDeTreino requestFichaDeTreino){
        Academia academia = repositoryAcademia.getReferenceById(requestFichaDeTreino.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestFichaDeTreino.getIdProfessor());

        Aluno aluno = repositoryAluno.getReferenceByCpf(requestFichaDeTreino.getCpfAluno()).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + requestFichaDeTreino.getCpfAluno() + " não encontrado."));

        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.getReferenceById(requestFichaDeTreino.getIdFicha());
        var endereco = academia.getEndereco();

        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        fichaDeTreino.setAluno(aluno);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAcademiaAfiliada(academia);
        repositoryFichaDeTreino.save(fichaDeTreino);

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


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public ResponseFichaDeTreino deletarFicha(Long idFicha){
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha).map(f -> f)
                .orElseThrow(() -> new DataBindingViolationException("Ficha de ID"+idFicha+" não pode ser deletada."));
        try {
            repositoryFichaDeTreino.delete(fichaDeTreino);
        }catch (Exception e){
            throw new DataBindingViolationException("A ficha de treino não pode ser deletada, por precisar deletar entidades relacionas");
        }
        var academia = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();
        var professor = fichaDeTreino.getProfessor();

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

        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
}
