package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.exception.ErrorException;
import Projeto.Academia.controller.DTO.*;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestAluno;
import Projeto.Academia.controller.response.ResponseAluno;
import Projeto.Academia.exception.ObjectNotFoundException;
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

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());
        professorDTO.setAcademiasAfiliada(academiaDTO);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setIdAcademiaAfiliada(academiaDTO);
        alunoDTO.setCrefProfessor(professorDTO);

        ResponseAluno responseAluno = new ResponseAluno();
        responseAluno.setAlunoDTO(alunoDTO);

        return responseAluno;

    }
    public List<ResponseAluno> listarAlunos(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("academia com o ID:" + idAcademia + " não encontrada."));

        var endereco = academia.getEndereco();

        List<Aluno> alunos = repositoryAluno.findByAcademiaAfiliada(academia);
        List<ResponseAluno> responseAlunos = new ArrayList<>();

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

        for (Aluno aluno : alunos){

            var professor = aluno.getProfessor();

            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(professor.getId());
            professorDTO.setNome(professor.getNome());
            professorDTO.setCpf(professor.getCpf());
            professorDTO.setCref(professor.getCref());

            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setCpf(aluno.getCpf());
            alunoDTO.setIdAcademiaAfiliada(academiaDTO);
            alunoDTO.setCrefProfessor(professorDTO);

            alunoDTO.setCrefProfessor(professorDTO);
            alunoDTO.getCrefProfessor();

            ResponseAluno responseAluno = new ResponseAluno();
            responseAluno.setAlunoDTO(alunoDTO);

            responseAlunos.add(responseAluno);
        }
        return responseAlunos;
    }
    public ResponseAluno buscarAluno(String cpfAluno){
        Aluno aluno = repositoryAluno.findByCpf(cpfAluno).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + cpfAluno + " não encontrado."));

        var academia = aluno.getAcademiaAfiliada();
        var professor = aluno.getProfessor();

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setIdAcademiaAfiliada(academiaDTO);
        alunoDTO.setCrefProfessor(professorDTO);

        ResponseAluno responseAluno = new ResponseAluno();
        responseAluno.setAlunoDTO(alunoDTO);

        return responseAluno;
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

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());
        personalDTO.setCpf(personal.getCpf());
        personalDTO.setCref(personal.getCref());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());
        alunoDTO.setCrefPersonal(personalDTO);
        alunoDTO.setCrefProfessor(professorDTO);
        alunoDTO.setIdAcademiaAfiliada(academiaDTO);

        ResponseAluno responseAluno = new ResponseAluno();
        responseAluno.setAlunoDTO(alunoDTO);

        return responseAluno;
    }
    public ResponseAluno deletarAluno(Long idAluno){
        Aluno aluno = repositoryAluno.findById(idAluno).map(a -> a)
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

        repositoryAluno.delete(aluno);

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());

        ResponseAluno responseAluno = new ResponseAluno();
        responseAluno.setAlunoDTO(alunoDTO);

        return responseAluno;
    }
}
