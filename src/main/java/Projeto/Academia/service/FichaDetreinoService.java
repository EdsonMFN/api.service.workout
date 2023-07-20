package Projeto.Academia.service;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestFichaDeTreino;
import Projeto.Academia.controller.response.ResponseFichaDeTreino;
import Projeto.Academia.repositorys.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
