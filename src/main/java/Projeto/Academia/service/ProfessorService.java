package Projeto.Academia.service;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryAluno;
import Projeto.Academia.repositorys.RepositoryFichaDeTreino;
import Projeto.Academia.repositorys.RepositoryProfessor;
import Projeto.Academia.controller.request.RequestProfessor;
import Projeto.Academia.controller.response.ResponseProfessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseProfessor criarProfessor(Long idAcademia,RequestProfessor requestProfessor){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);

        Professor professor = new Professor();
        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        professor.setAcademiaAfiliada(academia.get());
        repositoryProfessor.save(professor);

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
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());
        professorDTO.setAcademiasAfiliada(academiaDTO);

        ResponseProfessor responseProfessor = new ResponseProfessor();
        responseProfessor.setProfessorDTO(professorDTO);

        return responseProfessor;
    }
    public List<ResponseProfessor> listarProfessor(Long idAcademia){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);

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

        List<Professor> professores = repositoryProfessor.findAllByAcademiaAfiliada(academia.get());
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        List<ResponseProfessor> responseProfessores = new ArrayList<>();

        for (Professor professor : professores){

            ProfessorDTO professorDTO = new ProfessorDTO();
            professorDTO.setId(professor.getId());
            professorDTO.setNome(professor.getNome());
            professorDTO.setCpf(professor.getCpf());
            professorDTO.setCref(professor.getCref());

            academiaDTO.setProfessores(professorDTOS);
            academiaDTO.getProfessores().add(professorDTO);

            ResponseProfessor responseProfessor = new ResponseProfessor();
            responseProfessor.setProfessorDTO(professorDTO);

            responseProfessores.add(responseProfessor);
        }
        return responseProfessores;
    }
    public ResponseProfessor buscarProfessor(Long idProfessor){
        Optional<Professor> professor = repositoryProfessor.findById(idProfessor);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(professor.get().getAcademiaAfiliada().getEndereco().getId());
        enderecoDTO.setCep(professor.get().getAcademiaAfiliada().getEndereco().getCep());
        enderecoDTO.setEstado(professor.get().getAcademiaAfiliada().getEndereco().getEstado());
        enderecoDTO.setCidade(professor.get().getAcademiaAfiliada().getEndereco().getCidade());
        enderecoDTO.setBairro(professor.get().getAcademiaAfiliada().getEndereco().getBairro());
        enderecoDTO.setNumero(professor.get().getAcademiaAfiliada().getEndereco().getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(professor.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(professor.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(professor.get().getAcademiaAfiliada().getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.get().getId());
        professorDTO.setNome(professor.get().getNome());
        professorDTO.setCpf(professor.get().getCpf());
        professorDTO.setCref(professor.get().getCref());
        professorDTO.setAcademiasAfiliada(academiaDTO);

        ResponseProfessor responseProfessor = new ResponseProfessor();
        responseProfessor.setProfessorDTO(professorDTO);

        return responseProfessor;
    }
    public ResponseProfessor alterarProfessor(RequestProfessor requestProfessor){
        Professor professor = repositoryProfessor.getReferenceById(requestProfessor.getIdProfessor());

        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        repositoryProfessor.save(professor);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        professorDTO.setCpf(professor.getCpf());
        professorDTO.setCref(professor.getCref());

        ResponseProfessor responseProfessor = new ResponseProfessor();
        responseProfessor.setProfessorDTO(professorDTO);

        return responseProfessor;
    }
    public ResponseProfessor deletarProfessor(Long idProfessor){
        Optional<Professor> professor = repositoryProfessor.findById(idProfessor);
        repositoryProfessor.deleteById(idProfessor);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(professor.get().getAcademiaAfiliada().getEndereco().getId());
        enderecoDTO.setCep(professor.get().getAcademiaAfiliada().getEndereco().getCep());
        enderecoDTO.setEstado(professor.get().getAcademiaAfiliada().getEndereco().getEstado());
        enderecoDTO.setCidade(professor.get().getAcademiaAfiliada().getEndereco().getCidade());
        enderecoDTO.setBairro(professor.get().getAcademiaAfiliada().getEndereco().getBairro());
        enderecoDTO.setNumero(professor.get().getAcademiaAfiliada().getEndereco().getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(professor.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(professor.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(professor.get().getAcademiaAfiliada().getCnpj());
        academiaDTO.setEndereco(enderecoDTO);

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(professor.get().getId());
        professorDTO.setNome(professor.get().getNome());
        professorDTO.setCpf(professor.get().getCpf());
        professorDTO.setCref(professor.get().getCref());
        professorDTO.setAcademiasAfiliada(academiaDTO);

        ResponseProfessor responseProfessor = new ResponseProfessor();
        responseProfessor.setProfessorDTO(professorDTO);

        return responseProfessor;
    }
}
