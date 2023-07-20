package Projeto.Academia.service;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.AlunoDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestAluno;
import Projeto.Academia.controller.response.ResponseAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public ResponseAluno criarAluno(Long idAcademia,Long idProfessor, RequestAluno requestAluno){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        Optional<Professor> professor = repositoryProfessor.findById(idProfessor);

        Aluno aluno = new Aluno();
        aluno.setNome(requestAluno.getNome());
        aluno.setCpf(requestAluno.getCpf());
        aluno.setAcademiaAfiliada(academia.get());
        aluno.setProfessor(professor.get());
        repositoryAluno.save(aluno);

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

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.get().getId());
        professorDTO.setNome(professor.get().getNome());
        professorDTO.setCpf(professor.get().getCpf());
        professorDTO.setCref(professor.get().getCref());
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
}
