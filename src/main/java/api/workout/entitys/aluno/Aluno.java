package api.workout.entitys.aluno;

import api.workout.entitys.academia.Academia;
import api.workout.entitys.fichaDeTreino.FichaDeTreino;
import api.workout.entitys.personal.Personal;
import api.workout.entitys.professor.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Aluno")
@Table(name = "aluno")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno",insertable=false, updatable=false)
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academia_afiliada",nullable = false)
    private Academia academiaAfiliada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cref_professor",nullable = false)
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cref_personal")
    private Personal personal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aluno")
    private List<FichaDeTreino> fichaDeTreino;
}