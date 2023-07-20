package Projeto.Academia.service;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.AlunoDTO;
import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.controller.DTO.PersonalDTO;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.service.request.RequestPersonal;
import Projeto.Academia.service.response.ResponsePersonal;
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
}
