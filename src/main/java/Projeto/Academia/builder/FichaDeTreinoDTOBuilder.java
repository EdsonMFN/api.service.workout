package Projeto.Academia.builder;

import Projeto.Academia.controller.DTO.AcademiaDTO;
import Projeto.Academia.controller.DTO.AlunoDTO;
import Projeto.Academia.controller.DTO.FichaDeTreinoDTO;
import Projeto.Academia.controller.DTO.ProfessorDTO;
import Projeto.Academia.entitys.fichaDeTreino.Exercicio;

public final class FichaDeTreinoDTOBuilder {
    private Long id;
    private Exercicio exercicio;
    private ProfessorDTO professor;
    private AlunoDTO aluno;
    private AcademiaDTO academiaAfiliada;

    private FichaDeTreinoDTOBuilder() {
    }

    public static FichaDeTreinoDTOBuilder fichaDeTreinoDTOBuilder() {
        return new FichaDeTreinoDTOBuilder();
    }

    public FichaDeTreinoDTOBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public FichaDeTreinoDTOBuilder exercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
        return this;
    }

    public FichaDeTreinoDTOBuilder professor(ProfessorDTO professor) {
        this.professor = professor;
        return this;
    }

    public FichaDeTreinoDTOBuilder aluno(AlunoDTO aluno) {
        this.aluno = aluno;
        return this;
    }

    public FichaDeTreinoDTOBuilder academiaAfiliada(AcademiaDTO academiaAfiliada) {
        this.academiaAfiliada = academiaAfiliada;
        return this;
    }

    public FichaDeTreinoDTO build() {
        FichaDeTreinoDTO fichaDeTreinoDTO = new FichaDeTreinoDTO();
        fichaDeTreinoDTO.setId(id);
        fichaDeTreinoDTO.setExercicio(exercicio);
        fichaDeTreinoDTO.setProfessor(professor);
        fichaDeTreinoDTO.setAluno(aluno);
        fichaDeTreinoDTO.setAcademiaAfiliada(academiaAfiliada);
        return fichaDeTreinoDTO;
    }
}
