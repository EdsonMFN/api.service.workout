package Projeto.Academia.service;

import java.util.ArrayList;
import java.util.List;

import Projeto.Academia.exception.ErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Projeto.Academia.controller.request.RequestProfessor;
import Projeto.Academia.controller.response.ResponseProfessor;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.professor.Professor;
import Projeto.Academia.repositorys.RepositoryAcademia;
import Projeto.Academia.repositorys.RepositoryAluno;
import Projeto.Academia.repositorys.RepositoryFichaDeTreino;
import Projeto.Academia.repositorys.RepositoryProfessor;
import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.repositorys.DTO.ProfessorDTO;

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

        ResponseProfessor responseProfessor = new ResponseProfessor();
        responseProfessor.setProfessorDTO(professorDTO);

        return responseProfessor;
    }
    public List<ResponseProfessor> listarProfessor(Long idAcademia){
        Academia academia= repositoryAcademia.findById(idAcademia).orElseThrow(()-> new NullPointerException("Academia não encontrada"));

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

        List<Professor> professores = repositoryProfessor.findAllByAcademiaAfiliada(academia);
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
        Professor professor = repositoryProfessor.findById(idProfessor).map(p->p)
                .orElseThrow(()-> new ErrorException("professor não encontrado"));

        var academiaAfiliada = professor.getAcademiaAfiliada();
        var endereco = professor.getAcademiaAfiliada().getEndereco();

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());
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
    public ResponseProfessor alterarProfessor(RequestProfessor requestProfessor){
        Professor professor = repositoryProfessor.getReferenceById(requestProfessor.getIdProfessor());
        Academia academia = repositoryAcademia.getReferenceById(requestProfessor.getIdAcademia());

        var academiaAfiliada = professor.getAcademiaAfiliada();

        professor.setNome(requestProfessor.getNome());
        professor.setCpf(requestProfessor.getCpf());
        professor.setCref(requestProfessor.getCref());
        professor.setAcademiaAfiliada(academia);
        repositoryProfessor.save(professor);

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());

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
    public ResponseProfessor deletarProfessor(Long idProfessor){
        Professor professor = repositoryProfessor.findById(idProfessor).map(p->p)
                .orElseThrow(()-> new ErrorException("professor não encontrado"));

        repositoryProfessor.deleteById(idProfessor);

        var academiaAfiliada = professor.getAcademiaAfiliada();
        var endereco = professor.getAcademiaAfiliada().getEndereco();

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(endereco.getId());
        enderecoDTO.setCep(endereco.getCep());
        enderecoDTO.setEstado(endereco.getEstado());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setNumero(endereco.getNumero());

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academiaAfiliada.getId());
        academiaDTO.setAcademiaAfiliada(academiaAfiliada.getAcademiaAfiliada());
        academiaDTO.setCnpj(academiaAfiliada.getCnpj());
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
}
