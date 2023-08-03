package Projeto.Academia.builder;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.AlunoDTO;
import Projeto.Academia.controller.DTO.FichaDeTreinoDTO;
import Projeto.Academia.controller.DTO.ProfessorDTO;

import java.util.List;

public final class ProfessorDTOBuilder {
    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO academiasAfiliada;
    private List<AlunoDTO> alunos;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    private ProfessorDTOBuilder() {
    }

    public static ProfessorDTOBuilder professorDTOBuilder() {
        return new ProfessorDTOBuilder();
    }

    public ProfessorDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProfessorDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ProfessorDTOBuilder cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ProfessorDTOBuilder cref(String cref) {
        this.cref = cref;
        return this;
    }

    public ProfessorDTOBuilder academiasAfiliada(AcademiaDTO academiasAfiliada) {
        this.academiasAfiliada = academiasAfiliada;
        return this;
    }

    public ProfessorDTOBuilder alunos(List<AlunoDTO> alunos) {
        this.alunos = alunos;
        return this;
    }

    public ProfessorDTOBuilder fichaDeTreinos(List<FichaDeTreinoDTO> fichaDeTreinos) {
        this.fichaDeTreinos = fichaDeTreinos;
        return this;
    }

    public ProfessorDTO build() {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setId(id);
        professorDTO.setNome(nome);
        professorDTO.setCpf(cpf);
        professorDTO.setCref(cref);
        professorDTO.setAcademiasAfiliada(academiasAfiliada);
        professorDTO.setAlunos(alunos);
        professorDTO.setFichaDeTreinos(fichaDeTreinos);
        return professorDTO;
    }
}
