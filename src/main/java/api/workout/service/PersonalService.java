package api.workout.service;

import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Aluno;
import api.workout.domains.entitys.Personal;
import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.AlunoDTO;
import api.workout.domains.model.PersonalDTO;
import api.workout.domains.repositorys.*;
import api.workout.exception.handles.DataBindingViolationException;
import api.workout.exception.handles.ErrorException;
import api.workout.exception.handles.ObjectNotFoundException;
import api.workout.rest.request.RequestPersonal;
import api.workout.rest.response.ResponsePersonal;
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
//        var endereco = academia.getEndereco();

        Personal personal = new Personal(requestPersonal);
        personal.setAcademiaAfiliada(academia);
        repositoryPersonal.save(personal);

//        EnderecoDTO enderecoDTO = EnderecoDTO
//                .builder()
//                .id(endereco.getId())
//                .cep(endereco.getCep())
//                .estado(endereco.getEstado())
//                .bairro(endereco.getBairro())
//                .cidade(endereco.getCidade())
//                .numero(endereco.getNumero())
//                .build();
//
//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .endereco(enderecoDTO)
//                .build();

        return new ResponsePersonal(new PersonalDTO(personal));
    }
    public List<ResponsePersonal> listarPersonal(Long idAcademia){
        Academia academia = repositoryAcademia.findById(idAcademia)
                .orElseThrow(() -> new ErrorException("academia não encontrada."));

//        var endereco = academia.getEndereco();

        List<Personal> personals = repositoryPersonal.findByAcademiaAfiliada(academia);
        List<ResponsePersonal> responsePersonals = new ArrayList<>();

//        EnderecoDTO enderecoDTO = EnderecoDTO
//                .builder()
//                .id(endereco.getId())
//                .cep(endereco.getCep())
//                .estado(endereco.getEstado())
//                .bairro(endereco.getBairro())
//                .cidade(endereco.getCidade())
//                .numero(endereco.getNumero())
//                .build();
//
//        AcademiaDTO academiaDTO = AcademiaDTOBuilder
//                .academiaDTOBuilder()
//                .id(academia.getId())
//                .academiaAfiliada(academia.getAcademiaAfiliada())
//                .cnpj(academia.getCnpj())
//                .endereco(enderecoDTO)
//                .build();

        personals.forEach(personal -> {
            ResponsePersonal responsePersonal =
                    new ResponsePersonal(new PersonalDTO(personal));

            responsePersonals.add(responsePersonal);
        });
        return responsePersonals;
    }
    public ResponsePersonal buscarPersonal(Long idPersonal){
        Personal personal = repositoryPersonal.findById(idPersonal)
                .orElseThrow(() -> new ObjectNotFoundException("personal com o ID "+idPersonal+ " não encontrado."));

        List<Aluno> alunos = repositoryAluno.findAll();

        var academia = personal.getAcademiaAfiliada();

        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        AcademiaDTO academiaDTO = new AcademiaDTO(academia);

        PersonalDTO personalDTO = PersonalDTO.builder()
                .id(personal.getId())
                .nome(personal.getNome())
                .cpf(personal.getCpf())
                .cref(personal.getCref())
                .idAcademiasAfiliada(academiaDTO)
                .alunos(alunoDTOS)
                .build();

        alunos.parallelStream().forEach(aluno -> {
            AlunoDTO alunoDTO = AlunoDTO.builder()
                    .id(aluno.getId())
                    .cpf(aluno.getCpf())
                    .nome(aluno.getNome())
                    .academiaAfiliada(academiaDTO)
                    .personal(personalDTO)
                    .build();

            if (personal.equals(aluno.getPersonal())){
                personalDTO.setAlunos(alunoDTOS);
                personalDTO.getAlunos().add(alunoDTO);
            }
        });
        return new ResponsePersonal(new PersonalDTO(personal));
    }
    public ResponsePersonal alterarPersonal(RequestPersonal requestPersonal){
        Personal personal = repositoryPersonal.getReferenceById(requestPersonal.getId());
        Academia academia = repositoryAcademia.getReferenceById(requestPersonal.getIdAcademia());

        personal.setNome(requestPersonal.getNome());
        personal.setAcademiaAfiliada(academia);
        repositoryPersonal.save(personal);

        return new ResponsePersonal(new PersonalDTO(personal));
    }
    public ResponsePersonal deletarPersoanl(Long idPersonal){
        Personal personal = repositoryPersonal.findById(idPersonal)
                .orElseThrow(() -> new DataBindingViolationException("personal" + idPersonal +"não pode ser deletado por conflito com entidades"));
        try {
            repositoryPersonal.delete(personal);
        }catch (Exception e){
            throw new DataBindingViolationException("O personal não pode ser deletado, por precisar deletar entidades relacionas");
        }
        return new ResponsePersonal(new PersonalDTO(personal));
    }
}
