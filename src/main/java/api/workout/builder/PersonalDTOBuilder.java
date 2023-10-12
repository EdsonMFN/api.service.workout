package api.workout.builder;

import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.AlunoDTO;
import api.workout.domains.model.PersonalDTO;

import java.util.List;

public final class PersonalDTOBuilder {
    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO idAcademiasAfiliada;
    private List<AlunoDTO> alunos;

    private PersonalDTOBuilder() {
    }

    public static PersonalDTOBuilder personalDTOBuilder() {
        return new PersonalDTOBuilder();
    }

    public PersonalDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public PersonalDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PersonalDTOBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public PersonalDTOBuilder cref(String cref) {
        this.cref = cref;
        return this;
    }

    public PersonalDTOBuilder idAcademiasAfiliada(AcademiaDTO idAcademiasAfiliada) {
        this.idAcademiasAfiliada = idAcademiasAfiliada;
        return this;
    }

    public PersonalDTOBuilder alunos(List<AlunoDTO> alunos) {
        this.alunos = alunos;
        return this;
    }

    public PersonalDTO build() {
        PersonalDTO personalDTO = new PersonalDTO();
        personalDTO.setId(id);
        personalDTO.setNome(nome);
        personalDTO.setCpf(cpf);
        personalDTO.setCref(cref);
        personalDTO.setIdAcademiasAfiliada(idAcademiasAfiliada);
        personalDTO.setAlunos(alunos);
        return personalDTO;
    }
}
