package Projeto.Academia.service;

import java.util.ArrayList;
import java.util.List;

import Projeto.Academia.exception.ErrorException;
import Projeto.Academia.exception.ObjectNotFoundException;
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
import Projeto.Academia.controller.DTO.*;


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

        FichaDeTreino fichaDeTreino = new FichaDeTreino();
        fichaDeTreino.setAcademiaAfiliada(academia);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAluno(aluno);
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
        Aluno aluno = repositoryAluno.findByCpf(cpfAluno).map(a -> a)
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

        var professor = aluno.getProfessor();
        var academiaAfiliada = aluno.getAcademiaAfiliada();

        List<FichaDeTreino> fichaDeTreinos = repositoryFichaDeTreino.findByAluno(aluno);
        List<ResponseFichaDeTreino> responseFichaDeTreinos = new ArrayList<>();

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());

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
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha).map(f -> f)
                .orElseThrow(() -> new ObjectNotFoundException("Ficha com o ID"+idFicha+" não encontrada."));

        var academiaAfiliada = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(fichaDeTreino.getProfessor().getId());
        professorDTO.setNome(fichaDeTreino.getProfessor().getNome());
        professorDTO.setCpf(fichaDeTreino.getProfessor().getCpf());
        professorDTO.setCref(fichaDeTreino.getProfessor().getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());

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
    public ResponseFichaDeTreino alterarFicha (RequestFichaDeTreino requestFichaDeTreino){
        Academia academia = repositoryAcademia.getReferenceById(requestFichaDeTreino.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestFichaDeTreino.getIdProfessor());

        Aluno aluno = repositoryAluno.getReferenceByCpf(requestFichaDeTreino.getCpfAluno()).map(a -> a)
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + requestFichaDeTreino.getCpfAluno() + " não encontrado."));

        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.getReferenceById(requestFichaDeTreino.getIdFicha());

        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        fichaDeTreino.setAluno(aluno);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAcademiaAfiliada(academia);
        repositoryFichaDeTreino.save(fichaDeTreino);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());

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
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha).map(f -> f)
                .orElseThrow(() -> new ErrorException("Ficha não encontrada."));

        var academiaAfiliada = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();
        var professor = fichaDeTreino.getProfessor();

        repositoryFichaDeTreino.delete(fichaDeTreino);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());

        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setNome(aluno.getNome());
        alunoDTO.setCpf(aluno.getCpf());

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
}
