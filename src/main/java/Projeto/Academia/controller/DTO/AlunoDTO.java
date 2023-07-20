package Projeto.Academia.controller.DTO;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.fichaDeTreino.FichaDeTreino;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.entitys.professor.Professor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlunoDTO {
    private Long id;
    private String nome;
    private String cpf;
    private AcademiaDTO idAcademiaAfiliada;
    private ProfessorDTO crefProfessor;
    private PersonalDTO crefPersonal;
    private List<FichaDeTreinoDTO> fichaDeTreinos;
}
