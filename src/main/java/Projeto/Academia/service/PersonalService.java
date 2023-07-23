package Projeto.Academia.service;

import Projeto.Academia.repositorys.DTO.AcademiaDTO;
import Projeto.Academia.repositorys.DTO.AlunoDTO;
import Projeto.Academia.repositorys.DTO.EnderecoDTO;
import Projeto.Academia.repositorys.DTO.PersonalDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestPersonal;
import Projeto.Academia.controller.response.ResponsePersonal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalService {
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

    public ResponsePersonal criarPersonal(Long idAcademia, String cpfAluno,RequestPersonal requestPersonal){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        List<Aluno> alunos = repositoryAluno.findAllByCpf(cpfAluno);

        Personal personal = new Personal();
        personal.setNome(requestPersonal.getNome());
        personal.setCpf(requestPersonal.getCpf());
        personal.setCref(requestPersonal.getCref());
        personal.setAcademiaAfiliada(academia.get());
        personal.setAluno(alunos);
        repositoryPersonal.save(personal);

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

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());
        personalDTO.setCpf(personal.getCpf());
        personalDTO.setCref(personal.getCref());
        personalDTO.setIdAcademiasAfiliada(academiaDTO);

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        List<AlunoDTO> alunosDTO = new ArrayList<>();

        for(Aluno aluno : alunos){

            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setCpf(aluno.getCpf());
            alunoDTO.setIdAcademiaAfiliada(academiaDTO);

            personalDTO.setAlunos(alunosDTO);
            personalDTO.getAlunos().add(alunoDTO);
        }
        return responsePersonal;
    }
    public List<ResponsePersonal> listarPersonal(Long idAcademia){
        Optional<Academia> academia = repositoryAcademia.findById(idAcademia);
        List<Personal> personals = repositoryPersonal.findByAcademiaAfiliada(academia.get());
        List<ResponsePersonal> responsePersonals = new ArrayList<>();

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

        for (Personal personal : personals){

            personal.setNome(personal.getNome());
            personal.setCpf(personal.getCpf());
            personal.setCref(personal.getCref());
            personal.setAcademiaAfiliada(personal.getAcademiaAfiliada());

            PersonalDTO personalDTO = new PersonalDTO();
            personalDTO.setId(personal.getId());
            personalDTO.setNome(personal.getNome());
            personalDTO.setCpf(personal.getCpf());
            personalDTO.setCref(personal.getCref());
            personalDTO.setIdAcademiasAfiliada(academiaDTO);

            ResponsePersonal responsePersonal = new ResponsePersonal();
            responsePersonal.setPersonalDTO(personalDTO);

            responsePersonals.add(responsePersonal);
        }
        return responsePersonals;
    }
    public ResponsePersonal buscarPersonal(Long idPersonal){
        Optional<Personal> personal = repositoryPersonal.findById(idPersonal);
        List<Aluno> alunos = repositoryAluno.findAll();

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(personal.get().getAcademiaAfiliada().getId());
        academiaDTO.setAcademiaAfiliada(personal.get().getAcademiaAfiliada().getAcademiaAfiliada());
        academiaDTO.setCnpj(personal.get().getAcademiaAfiliada().getCnpj());

        List<AlunoDTO> alunosDTO = new ArrayList<>();

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.get().getId());
        personalDTO.setNome(personal.get().getNome());
        personalDTO.setCpf(personal.get().getCpf());
        personalDTO.setCref(personal.get().getCref());
        personalDTO.setIdAcademiasAfiliada(academiaDTO);
        personalDTO.setAlunos(alunosDTO);

        for(Aluno aluno : alunos){

            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setCpf(aluno.getCpf());
            alunoDTO.setIdAcademiaAfiliada(academiaDTO);

            personalDTO.setAlunos(alunosDTO);
            personalDTO.getAlunos().add(alunoDTO);
        }


        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
    public ResponsePersonal alterarPersonal(RequestPersonal requestPersonal){
        Personal personal = repositoryPersonal.getReferenceById(requestPersonal.getIdPersonal());

        personal.setNome(requestPersonal.getNome());
        repositoryPersonal.save(personal);

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
    public ResponsePersonal deletarPersoanl(Long idPersonal){
        Optional<Personal> personal = repositoryPersonal.findById(idPersonal);
        repositoryPersonal.delete(personal.get());

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.get().getId());
        personalDTO.setNome(personal.get().getNome());
        personalDTO.setCpf(personal.get().getCpf());
        personalDTO.setCref(personal.get().getCref());

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
}
