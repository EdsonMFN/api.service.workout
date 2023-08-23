package api.workout.builder;


import api.workout.rest.DTO.*;

import java.util.List;

public final class AcademiaDTOBuilder {
    private Long id;
    private String academiaAfiliada;
    private EnderecoDTO endereco;
    private String cnpj;
    private List<AlunoDTO> alunos;
    private List<ProfessorDTO> professores;
    private List<PersonalDTO> personals;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    private AcademiaDTOBuilder() {
    }

    public static AcademiaDTOBuilder academiaDTOBuilder() {
        return new AcademiaDTOBuilder();
    }

    public AcademiaDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AcademiaDTOBuilder academiaAfiliada(String academiaAfiliada) {
        this.academiaAfiliada = academiaAfiliada;
        return this;
    }

    public AcademiaDTOBuilder endereco(EnderecoDTO endereco) {
        this.endereco = endereco;
        return this;
    }

    public AcademiaDTOBuilder cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public AcademiaDTOBuilder alunos(List<AlunoDTO> alunos) {
        this.alunos = alunos;
        return this;
    }

    public AcademiaDTOBuilder professores(List<ProfessorDTO> professores) {
        this.professores = professores;
        return this;
    }

    public AcademiaDTOBuilder personals(List<PersonalDTO> personals) {
        this.personals = personals;
        return this;
    }

    public AcademiaDTOBuilder fichaDeTreinos(List<FichaDeTreinoDTO> fichaDeTreinos) {
        this.fichaDeTreinos = fichaDeTreinos;
        return this;
    }

    public AcademiaDTO build() {
        AcademiaDTO academiaDTO = new AcademiaDTO();
        academiaDTO.setId(id);
        academiaDTO.setAcademiaAfiliada(academiaAfiliada);
        academiaDTO.setEndereco(endereco);
        academiaDTO.setCnpj(cnpj);
        academiaDTO.setAlunos(alunos);
        academiaDTO.setProfessores(professores);
        academiaDTO.setPersonals(personals);
        academiaDTO.setFichaDeTreinos(fichaDeTreinos);
        return academiaDTO;
    }
}
