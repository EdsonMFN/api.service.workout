package api.workout.builder;


import api.workout.rest.DTO.*;

import java.util.List;

public final class AlunoDTOBuilder {
    private Long id;
    private String nome;
    private String cpf;
    private AcademiaDTO idAcademiaAfiliada;
    private ProfessorDTO crefProfessor;
    private PersonalDTO crefPersonal;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    private AlunoDTOBuilder() {
    }

    public static AlunoDTOBuilder alunoDTOBuilder() {
        return new AlunoDTOBuilder();
    }

    public AlunoDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AlunoDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AlunoDTOBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public AlunoDTOBuilder idAcademiaAfiliada(AcademiaDTO idAcademiaAfiliada) {
        this.idAcademiaAfiliada = idAcademiaAfiliada;
        return this;
    }

    public AlunoDTOBuilder crefProfessor(ProfessorDTO crefProfessor) {
        this.crefProfessor = crefProfessor;
        return this;
    }

    public AlunoDTOBuilder crefPersonal(PersonalDTO crefPersonal) {
        this.crefPersonal = crefPersonal;
        return this;
    }

    public AlunoDTOBuilder fichaDeTreinos(List<FichaDeTreinoDTO> fichaDeTreinos) {
        this.fichaDeTreinos = fichaDeTreinos;
        return this;
    }

    public AlunoDTO build() {
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(id);
        alunoDTO.setNome(nome);
        alunoDTO.setCpf(cpf);
        alunoDTO.setIdAcademiaAfiliada(idAcademiaAfiliada);
        alunoDTO.setCrefProfessor(crefProfessor);
        alunoDTO.setCrefPersonal(crefPersonal);
        alunoDTO.setFichaDeTreinos(fichaDeTreinos);
        return alunoDTO;
    }
}
