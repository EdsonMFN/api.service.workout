package Projeto.Academia.service;

import Projeto.Academia.service.exception.ErrorException;
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
import Projeto.Academia.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ResponsePersonal criarPersonal(RequestPersonal requestPersonal){
        Academia academia = repositoryAcademia.getReferenceById(requestPersonal.getIdAcademia());
        var endereco = academia.getEndereco();

        Personal personal = new Personal();
        personal.setNome(requestPersonal.getNome());
        personal.setCpf(requestPersonal.getCpf());
        personal.setCref(requestPersonal.getCref());
        personal.setAcademiaAfiliada(academia);
        repositoryPersonal.save(personal);

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

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());
        personalDTO.setCpf(personal.getCpf());
        personalDTO.setCref(personal.getCref());
        personalDTO.setIdAcademiasAfiliada(academiaDTO);

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
    public List<ResponsePersonal> listarPersonal(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ErrorException("academia não encontrada."));

        var endereco = academia.getEndereco();

        List<Personal> personals = repositoryPersonal.findByAcademiaAfiliada(academia);
        List<ResponsePersonal> responsePersonals = new ArrayList<>();

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
        Personal personal = repositoryPersonal.findById(idPersonal).map(p -> p)
                .orElseThrow(() -> new ObjectNotFoundException("personal com o ID "+idPersonal+ " não encontrado."));

        List<Aluno> alunos = repositoryAluno.findAll();

        var academia = personal.getAcademiaAfiliada();

        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(academia.getId());
        academiaDTO.setAcademiaAfiliada(academia.getAcademiaAfiliada());
        academiaDTO.setCnpj(academia.getCnpj());

        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());
        personalDTO.setCpf(personal.getCpf());
        personalDTO.setCref(personal.getCref());
        personalDTO.setIdAcademiasAfiliada(academiaDTO);
        personalDTO.setAlunos(alunoDTOS);

        for(Aluno aluno : alunos){

            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setNome(aluno.getNome());
            alunoDTO.setCpf(aluno.getCpf());
            alunoDTO.setIdAcademiaAfiliada(academiaDTO);

            if (personal.equals(aluno.getPersonal())){
                personalDTO.setAlunos(alunoDTOS);
                personalDTO.getAlunos().add(alunoDTO);
            }else break;

        }


        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
    public ResponsePersonal alterarPersonal(RequestPersonal requestPersonal){
        Personal personal = repositoryPersonal.getReferenceById(requestPersonal.getIdPersonal());
        Academia academia = repositoryAcademia.getReferenceById(requestPersonal.getIdAcademia());

        personal.setNome(requestPersonal.getNome());
        personal.setAcademiaAfiliada(academia);
        repositoryPersonal.save(personal);

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
    public ResponsePersonal deletarPersoanl(Long idPersonal){
        Personal personal = repositoryPersonal.findById(idPersonal).map(p -> p)
                .orElseThrow(() -> new ErrorException("personal não encontrado."));

        repositoryPersonal.delete(personal);

        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(personal.getId());
        personalDTO.setNome(personal.getNome());
        personalDTO.setCpf(personal.getCpf());
        personalDTO.setCref(personal.getCref());

        ResponsePersonal responsePersonal = new ResponsePersonal();
        responsePersonal.setPersonalDTO(personalDTO);

        return responsePersonal;
    }
}
