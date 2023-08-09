package Projeto.Academia.service;

import Projeto.Academia.builder.AcademiaDTOBuilder;
import Projeto.Academia.builder.AlunoDTOBuilder;
import Projeto.Academia.builder.PersonalDTOBuilder;
import Projeto.Academia.controller.DTO.EnderecoDTO;
import Projeto.Academia.controller.DTO.PersonalDTO;
import Projeto.Academia.exception.DataBindingViolationException;
import Projeto.Academia.exception.ErrorException;
import Projeto.Academia.controller.DTO.*;
import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.repositorys.*;
import Projeto.Academia.controller.request.RequestPersonal;
import Projeto.Academia.controller.response.ResponsePersonal;
import Projeto.Academia.exception.ObjectNotFoundException;
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

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        return new ResponsePersonal(PersonalDTOBuilder
                .personalDTOBuilder()
                .id(personal.getId())
                .nome(personal.getNome())
                .cpf(personal.getCpf())
                .cref(personal.getCref())
                .idAcademiasAfiliada(academiaDTO)
                .build());
    }
    public List<ResponsePersonal> listarPersonal(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia).map(a -> a)
                .orElseThrow(() -> new ErrorException("academia não encontrada."));

        var endereco = academia.getEndereco();

        List<Personal> personals = repositoryPersonal.findByAcademiaAfiliada(academia);
        List<ResponsePersonal> responsePersonals = new ArrayList<>();

        EnderecoDTO enderecoDTO = EnderecoDTO
                .builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .bairro(endereco.getBairro())
                .cidade(endereco.getCidade())
                .numero(endereco.getNumero())
                .build();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .endereco(enderecoDTO)
                .build();

        personals.parallelStream().forEach(personal -> {
            ResponsePersonal responsePersonal =
                    new ResponsePersonal(PersonalDTOBuilder
                    .personalDTOBuilder()
                    .id(personal.getId())
                    .nome(personal.getNome())
                    .cpf(personal.getCpf())
                    .cref(personal.getCref())
                    .idAcademiasAfiliada(academiaDTO)
                    .build());

            responsePersonals.add(responsePersonal);
        });
        return responsePersonals;
    }
    public ResponsePersonal buscarPersonal(Long idPersonal){
        Personal personal = repositoryPersonal.findById(idPersonal).map(p -> p)
                .orElseThrow(() -> new ObjectNotFoundException("personal com o ID "+idPersonal+ " não encontrado."));

        List<Aluno> alunos = repositoryAluno.findAll();

        var academia = personal.getAcademiaAfiliada();

        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .build();

        PersonalDTO personalDTO = PersonalDTOBuilder
                .personalDTOBuilder()
                .id(personal.getId())
                .nome(personal.getNome())
                .cpf(personal.getCpf())
                .cref(personal.getCref())
                .idAcademiasAfiliada(academiaDTO)
                .alunos(alunoDTOS)
                .build();

        alunos.parallelStream().forEach(aluno -> {
            AlunoDTO alunoDTO = AlunoDTOBuilder
                    .alunoDTOBuilder()
                    .id(aluno.getId())
                    .cpf(aluno.getCpf())
                    .nome(aluno.getNome())
                    .idAcademiaAfiliada(academiaDTO)
                    .crefPersonal(personalDTO)
                    .build();
            if (personal.equals(aluno.getPersonal())){
                personalDTO.setAlunos(alunoDTOS);
                personalDTO.getAlunos().add(alunoDTO);
            }
        });
        return new ResponsePersonal(personalDTO);
    }
    public ResponsePersonal alterarPersonal(RequestPersonal requestPersonal){
        Personal personal = repositoryPersonal.getReferenceById(requestPersonal.getIdPersonal());
        Academia academia = repositoryAcademia.getReferenceById(requestPersonal.getIdAcademia());

        personal.setNome(requestPersonal.getNome());
        personal.setAcademiaAfiliada(academia);
        repositoryPersonal.save(personal);

        return new ResponsePersonal(PersonalDTOBuilder
                .personalDTOBuilder()
                .id(personal.getId())
                .nome(personal.getNome())
                .build());
    }
    public ResponsePersonal deletarPersoanl(Long idPersonal){
        Personal personal = repositoryPersonal.findById(idPersonal).map(p -> p)
                .orElseThrow(() -> new DataBindingViolationException("personal" + idPersonal +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryPersonal.delete(personal);
        }catch (Exception e){
            throw new DataBindingViolationException("O personal não pode ser deletado, por precisar deletar entidades relacionas");
        }
        return new ResponsePersonal(PersonalDTOBuilder
                .personalDTOBuilder()
                .id(personal.getId())
                .nome(personal.getNome())
                .cpf(personal.getCpf())
                .cref(personal.getCref())
                .build());
    }
}
