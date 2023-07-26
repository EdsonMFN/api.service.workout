package Projeto.Academia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projeto.Academia.controller.request.RequestFichaDeTreino;
import Projeto.Academia.controller.response.ResponseFichaDeTreino;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryAluno;
import Projeto.Academia.repositorys.RepositoryEndereco;
import Projeto.Academia.repositorys.RepositoryFichaDeTreino;
import Projeto.Academia.repositorys.RepositoryPersonal;
import Projeto.Academia.repositorys.RepositoryProfessor;
import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.AlunoDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.repositorys.DTO.FichaDeTreinoDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;

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

    public ResponseFichaDeTreino criarFicha(Long idAcademia, Long idProfessor, String cpfAluno,RequestFichaDeTreino requestFichaDeTreino){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        Optional<Professor> professor = repositoryProfessor.findById(idProfessor);
        Optional<Aluno> aluno = repositoryAluno.findByCpf(cpfAluno);

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
        alunoDTO.setId(aluno.get().getId());
        alunoDTO.setNome(aluno.get().getNome());
        alunoDTO.setCpf(aluno.get().getCpf());
        alunoDTO.setIdAcademiaAfiliada(academiaDTO);
        alunoDTO.setCrefProfessor(professorDTO);

        FichaDeTreino fichaDeTreino = new FichaDeTreino();
        fichaDeTreino.setAcademiaAfiliada(academia.get());
        fichaDeTreino.setProfessor(professor.get());
        fichaDeTreino.setAluno(aluno.get());
        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        repositoryFichaDeTreino.save(fichaDeTreino);

        FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
        fichaDeTreinoDTO.setId(fichaDeTreino.getId());
        fichaDeTreinoDTO.setAcademiaAfiliada(academiaDTO);
        fichaDeTreinoDTO.setProfessor(professorDTO);
        fichaDeTreinoDTO.setAluno(alunoDTO);
        fichaDeTreinoDTO.setExercicio(fichaDeTreino.getExercicio());

        ResponseFichaDeTreino responseFichaDeTreino = new ResponseFichaDeTreino();
        responseFichaDeTreino.setFichaDeTreinoDTO(fichaDeTreinoDTO);

        return responseFichaDeTreino;
    }
    public List<ResponseFichaDeTreino> listarFichas(String cpfAluno){
        Optional<Aluno> aluno = repositoryAluno.findByCpf(cpfAluno);
        List<FichaDeTreino> fichaDeTreinos = repositoryFichaDeTreino.findByAluno(aluno.get());
        List<ResponseFichaDeTreino> responseFichaDeTreinos = new ArrayList<>();
        List<FichaDeTreinoDTO> fichaDeTreinoDTOS = new ArrayList<>();

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(aluno.get().getProfessor().getId());
        professorDTO.setNome(aluno.get().getProfessor().getNome());
        professorDTO.setCpf(aluno.get().getProfessor().getCpf());
        professorDTO.setCref(aluno.get().getProfessor().getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(aluno.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(aluno.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(aluno.get().getAcademiaAfiliada().getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.get().getId());
        alunoDTO.setNome(aluno.get().getNome());
        alunoDTO.setCpf(aluno.get().getCpf());

        for (FichaDeTreino fichaDeTreino : fichaDeTreinos){

            FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
            fichaDeTreinoDTO.setId(fichaDeTreino.getId());
            fichaDeTreinoDTO.setAcademiaAfiliada(academiaDTO);
            fichaDeTreinoDTO.setProfessor(professorDTO);
            fichaDeTreinoDTO.setAluno(alunoDTO);
            fichaDeTreinoDTO.setExercicio(fichaDeTreino.getExercicio());

            fichaDeTreinoDTO.setAluno(alunoDTO);
            fichaDeTreinoDTO.getAluno();

            ResponseFichaDeTreino responseFichaDeTreino = new ResponseFichaDeTreino();
            responseFichaDeTreino.setFichaDeTreinoDTO(fichaDeTreinoDTO);

            responseFichaDeTreinos.add(responseFichaDeTreino);
        }
        return responseFichaDeTreinos;
    }
    public ResponseFichaDeTreino buscarFicha(Long idFicha){
        Optional<FichaDeTreino> fichaDeTreino = repositoryFichaDeTreino.findById(idFicha);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(fichaDeTreino.get().getProfessor().getId());
        professorDTO.setNome(fichaDeTreino.get().getProfessor().getNome());
        professorDTO.setCpf(fichaDeTreino.get().getProfessor().getCpf());
        professorDTO.setCref(fichaDeTreino.get().getProfessor().getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(fichaDeTreino.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(fichaDeTreino.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(fichaDeTreino.get().getAcademiaAfiliada().getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(fichaDeTreino.get().getAluno().getId());
        alunoDTO.setNome(fichaDeTreino.get().getAluno().getNome());
        alunoDTO.setCpf(fichaDeTreino.get().getAluno().getCpf());

        FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
        fichaDeTreinoDTO.setId(fichaDeTreino.get().getId());
        fichaDeTreinoDTO.setAcademiaAfiliada(academiaDTO);
        fichaDeTreinoDTO.setProfessor(professorDTO);
        fichaDeTreinoDTO.setAluno(alunoDTO);
        fichaDeTreinoDTO.setExercicio(fichaDeTreino.get().getExercicio());

        ResponseFichaDeTreino responseFichaDeTreino = new ResponseFichaDeTreino();
        responseFichaDeTreino.setFichaDeTreinoDTO(fichaDeTreinoDTO);

        return responseFichaDeTreino;
    }
    public ResponseFichaDeTreino alterarFicha (Long idAcademia,String cpfAluno, Long idProfessor, RequestFichaDeTreino requestFichaDeTreino){
        Optional<Aluno> aluno = repositoryAluno.findByCpf(cpfAluno);
        Optional<Professor> professor = repositoryProfessor.findById(idProfessor);
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.getReferenceById(requestFichaDeTreino.getId());
        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        fichaDeTreino.setAluno(aluno.get());
        fichaDeTreino.setProfessor(professor.get());
        fichaDeTreino.setAcademiaAfiliada(academia.get());
        repositoryFichaDeTreino.save(fichaDeTreino);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(fichaDeTreino.getProfessor().getId());
        professorDTO.setNome(fichaDeTreino.getProfessor().getNome());
        professorDTO.setCpf(fichaDeTreino.getProfessor().getCpf());
        professorDTO.setCref(fichaDeTreino.getProfessor().getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(fichaDeTreino.getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(fichaDeTreino.getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(fichaDeTreino.getAcademiaAfiliada().getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(fichaDeTreino.getAluno().getId());
        alunoDTO.setNome(fichaDeTreino.getAluno().getNome());
        alunoDTO.setCpf(fichaDeTreino.getAluno().getCpf());

        FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
        fichaDeTreinoDTO.setId(fichaDeTreino.getId());
        fichaDeTreinoDTO.setAcademiaAfiliada(academiaDTO);
        fichaDeTreinoDTO.setProfessor(professorDTO);
        fichaDeTreinoDTO.setAluno(alunoDTO);
        fichaDeTreinoDTO.setExercicio(fichaDeTreino.getExercicio());

        ResponseFichaDeTreino responseFichaDeTreino = new ResponseFichaDeTreino();
        responseFichaDeTreino.setFichaDeTreinoDTO(fichaDeTreinoDTO);

        return responseFichaDeTreino;
    }
    public ResponseFichaDeTreino deletarFicha(Long idFicha){
        Optional<FichaDeTreino> fichaDeTreino = repositoryFichaDeTreino.findById(idFicha);
        repositoryFichaDeTreino.delete(fichaDeTreino.get());

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(fichaDeTreino.get().getProfessor().getId());
        professorDTO.setNome(fichaDeTreino.get().getProfessor().getNome());
        professorDTO.setCpf(fichaDeTreino.get().getProfessor().getCpf());
        professorDTO.setCref(fichaDeTreino.get().getProfessor().getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(fichaDeTreino.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(fichaDeTreino.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(fichaDeTreino.get().getAcademiaAfiliada().getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(fichaDeTreino.get().getAluno().getId());
        alunoDTO.setNome(fichaDeTreino.get().getAluno().getNome());
        alunoDTO.setCpf(fichaDeTreino.get().getAluno().getCpf());

        FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
        fichaDeTreinoDTO.setId(fichaDeTreino.get().getId());
        fichaDeTreinoDTO.setAcademiaAfiliada(academiaDTO);
        fichaDeTreinoDTO.setProfessor(professorDTO);
        fichaDeTreinoDTO.setAluno(alunoDTO);
        fichaDeTreinoDTO.setExercicio(fichaDeTreino.get().getExercicio());


        ResponseFichaDeTreino responseFichaDeTreino = new ResponseFichaDeTreino();
        responseFichaDeTreino.setFichaDeTreinoDTO(fichaDeTreinoDTO);

        return responseFichaDeTreino;
    }
}
