package Projeto.Academia.builder;

import Projeto.Academia.controller.DTO.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AcademiaDtoBuild {

    private Long id;
    private String academiaAfiliada;
    private EnderecoDTO endereco;
    private String cnpj;
    private List<AlunoDTO> alunos;
    private List<ProfessorDTO> professores;
    private List<PersonalDTO> personals;
    private List<FichaDeTreinoDTO> fichaDeTreinos;

    public static AcademiaDtoBuild academiaDtoBuild(){
        return new AcademiaDtoBuild();
    }

    public AcademiaDtoBuild id(Long id) {
        this.id = id;
        return this;
    }
    public AcademiaDtoBuild academiaAfiliada(String academiaAfiliada) {
        this.academiaAfiliada = academiaAfiliada;
        return this;
    }
    public AcademiaDtoBuild endereco(EnderecoDTO endereco) {
        this.endereco = endereco;
        return this;
    }
    public AcademiaDtoBuild cnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }
    public AcademiaDtoBuild alunos(List<AlunoDTO> alunos) {
        this.alunos = alunos;
        return this;
    }public AcademiaDtoBuild professores(List<ProfessorDTO> professores) {
        this.professores = professores;
        return this;
    }public AcademiaDtoBuild personals(List<PersonalDTO> personals) {
        this.personals = personals;
        return this;
    }public AcademiaDtoBuild fichaDeTreinos(List<FichaDeTreinoDTO> fichaDeTreinos) {
        this.fichaDeTreinos = fichaDeTreinos;
        return this;
    }
    public AcademiaDTO build(){
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
