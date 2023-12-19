package api.workout.service;

import api.workout.arquivo.ArquivoPdf;
import api.workout.arquivo.ArquivoTreinoXlsx;
import api.workout.builder.AcademiaDTOBuilder;
import api.workout.builder.AlunoDTOBuilder;
import api.workout.builder.FichaDeTreinoDTOBuilder;
import api.workout.builder.ProfessorDTOBuilder;
import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Aluno;
import api.workout.domains.entitys.ArquivoFichaTreino;
import api.workout.domains.entitys.FichaDeTreino;
import api.workout.enums.TipoDeArquivo;
import api.workout.domains.entitys.Professor;
import api.workout.domains.model.*;
import api.workout.domains.repositorys.*;
import api.workout.exception.handles.DataBindingViolationException;
import api.workout.exception.handles.ErrorException;
import api.workout.exception.handles.ObjectNotFoundException;
import api.workout.rest.request.RequestBaixarTreino;
import api.workout.rest.request.RequestFichaDeTreino;
import api.workout.rest.response.ResponseArquivoFichaTreino;
import api.workout.rest.response.ResponseFichaDeTreino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FichaDetreinoService {
    @Autowired
    private RepositoryAcademia repositoryAcademia;
    @Autowired
    private RepositoryProfessor repositoryProfessor;
    @Autowired
    private RepositoryAluno repositoryAluno;
    @Autowired
    private RepositoryFichaDeTreino repositoryFichaDeTreino;
    @Autowired
    private RepositoryBaixarTreino repositoryBaixarTreino;

    public ResponseFichaDeTreino criarFicha(RequestFichaDeTreino requestFichaDeTreino){
        Academia academia = repositoryAcademia.getReferenceById(requestFichaDeTreino.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestFichaDeTreino.getIdProfessor());

        Aluno aluno = repositoryAluno.getReferenceByCpf(requestFichaDeTreino.getCpfAluno())
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

        var endereco = academia.getEndereco();

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        FichaDeTreino fichaDeTreino = new FichaDeTreino();
        fichaDeTreino.setAcademiaAfiliada(academia);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAluno(aluno);
        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        repositoryFichaDeTreino.save(fichaDeTreino);

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public ResponseArquivoFichaTreino criarAquivoTreino(RequestBaixarTreino requestBaixarTreino){
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.getReferenceById(requestBaixarTreino.getIdFichaDeTreino());
        var aluno = fichaDeTreino.getAluno();
        var professor = fichaDeTreino.getProfessor();

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .build();

        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .crefProfessor(professorDTO)
                .build();

        FichaDeTreinoDTO fichaDeTreinoDTO = FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build();

        ArquivoFichaTreino arquivoFichaTreino = ArquivoFichaTreino
                .builder()
                .fichaDeTreino(fichaDeTreino)
                .tipoDeArquivo(requestBaixarTreino.getTipoDeArquivo())
                .build();
        repositoryBaixarTreino.save(arquivoFichaTreino);

        ArquivoFichaTreinoDTO arquivoFichaTreinoDTO = ArquivoFichaTreinoDTO.builder()
                .id(arquivoFichaTreino.getId())
                .fichaDeTreinoDTO(fichaDeTreinoDTO)
                .tipoDeArquivo(arquivoFichaTreino.getTipoDeArquivo())
                .base64(base64(arquivoFichaTreino,alunoDTO.getNome(), professorDTO.getNome()))
                .build();

        return new ResponseArquivoFichaTreino(arquivoFichaTreinoDTO);
    }

    public List<ResponseFichaDeTreino> listarFichas(String cpfAluno){
        Aluno aluno = repositoryAluno.findByCpf(cpfAluno)
                .orElseThrow(() -> new ErrorException("aluno não encontrado."));

        var professor = aluno.getProfessor();
        var academia = aluno.getAcademiaAfiliada();
        var endereco = aluno.getAcademiaAfiliada().getEndereco();

        List<FichaDeTreino> fichaDeTreinos = repositoryFichaDeTreino.findByAluno(aluno);
        List<ResponseFichaDeTreino> responseFichaDeTreinos = new ArrayList<>();

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        fichaDeTreinos.forEach(fichaDeTreino -> {
            ResponseFichaDeTreino responseFichaDeTreino =
                    new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                    .fichaDeTreinoDTOBuilder()
                    .id(fichaDeTreino.getId())
                    .academiaAfiliada(academiaDTO)
                    .professor(professorDTO)
                    .aluno(alunoDTO)
                    .exercicio(fichaDeTreino.getExercicio())
                    .build());

            responseFichaDeTreinos.add(responseFichaDeTreino);
        });
        return responseFichaDeTreinos;
    }
    public ResponseFichaDeTreino buscarFicha(Long idFicha){
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha)
                .orElseThrow(() -> new ObjectNotFoundException("Ficha com o ID"+idFicha+" não encontrada."));

        var academia = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();
        var endereco = academia.getEndereco();
        var professor = aluno.getProfessor();

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public ResponseFichaDeTreino alterarFicha (RequestFichaDeTreino requestFichaDeTreino){
        Academia academia = repositoryAcademia.getReferenceById(requestFichaDeTreino.getIdAcademia());
        Professor professor = repositoryProfessor.getReferenceById(requestFichaDeTreino.getIdProfessor());

        Aluno aluno = repositoryAluno.getReferenceByCpf(requestFichaDeTreino.getCpfAluno())
                .orElseThrow(() -> new ObjectNotFoundException("aluno com o CPF " + requestFichaDeTreino.getCpfAluno() + " não encontrado."));

        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.getReferenceById(requestFichaDeTreino.getIdFicha());
        var endereco = academia.getEndereco();

        fichaDeTreino.setExercicio(requestFichaDeTreino.getExercicio());
        fichaDeTreino.setAluno(aluno);
        fichaDeTreino.setProfessor(professor);
        fichaDeTreino.setAcademiaAfiliada(academia);
        repositoryFichaDeTreino.save(fichaDeTreino);

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

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();


        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    public ResponseFichaDeTreino deletarFicha(Long idFicha){
        FichaDeTreino fichaDeTreino = repositoryFichaDeTreino.findById(idFicha)
                .orElseThrow(() -> new DataBindingViolationException("Ficha de ID"+idFicha+" não pode ser deletada."));
        try {
            repositoryFichaDeTreino.delete(fichaDeTreino);
        }catch (Exception e){
            throw new DataBindingViolationException("A ficha de treino não pode ser deletada, por precisar deletar entidades relacionas");
        }
        var academia = fichaDeTreino.getAcademiaAfiliada();
        var aluno = fichaDeTreino.getAluno();
        var professor = fichaDeTreino.getProfessor();

        AcademiaDTO academiaDTO = AcademiaDTOBuilder
                .academiaDTOBuilder()
                .id(academia.getId())
                .academiaAfiliada(academia.getAcademiaAfiliada())
                .cnpj(academia.getCnpj())
                .build();

        ProfessorDTO professorDTO = ProfessorDTOBuilder
                .professorDTOBuilder()
                .id(professor.getId())
                .nome(professor.getNome())
                .cpf(professor.getCpf())
                .cref(professor.getCref())
                .academiasAfiliada(academiaDTO)
                .build();

        AlunoDTO alunoDTO= AlunoDTOBuilder
                .alunoDTOBuilder()
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .nome(aluno.getNome())
                .idAcademiaAfiliada(academiaDTO)
                .crefProfessor(professorDTO)
                .build();

        return new ResponseFichaDeTreino(FichaDeTreinoDTOBuilder
                .fichaDeTreinoDTOBuilder()
                .id(fichaDeTreino.getId())
                .academiaAfiliada(academiaDTO)
                .professor(professorDTO)
                .aluno(alunoDTO)
                .exercicio(fichaDeTreino.getExercicio())
                .build());
    }
    private String base64(ArquivoFichaTreino arquivoFichaTreino,String nomeAluno, String nomeProfessor){

        String base64Xlsx = null;
        String base64Pdf = null;
        if (arquivoFichaTreino.getTipoDeArquivo().equals(TipoDeArquivo.XLSX)) {
            ArquivoTreinoXlsx arquivoTreinoXlsx = new ArquivoTreinoXlsx();
            arquivoTreinoXlsx.executarArquivoTreino("D:\\Edson\\projetos\\workout\\manipulacao_de_arquivos\\excel-java",nomeAluno,nomeProfessor);
            base64Xlsx =  arquivoTreinoXlsx.base64Exel("D:\\Edson\\projetos\\workout\\manipulacao_de_arquivos\\excel-java");
        }
        else if (arquivoFichaTreino.getTipoDeArquivo().equals(TipoDeArquivo.PDF)){
            ArquivoPdf arquivoPdf = new ArquivoPdf();
            arquivoPdf.tranformarEmPdf("D:\\Edson\\projetos\\workout\\manipulacao_de_arquivos\\pdf");
            base64Pdf = arquivoPdf.base64Pdf("D:\\Edson\\projetos\\workout\\manipulacao_de_arquivos\\pdf");
        }

        return base64Pdf;
    }
}
