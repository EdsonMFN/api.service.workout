package api.workout.domains.entitys.professor;

import api.workout.domains.entitys.academia.Academia;
import api.workout.domains.entitys.aluno.Aluno;
import api.workout.domains.entitys.fichaDeTreino.FichaDeTreino;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Professor")
@Table(name = "professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cref")
    private String cref;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academia_afiliada",nullable = false)
    private Academia academiaAfiliada;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<Aluno> aluno;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "professor")
    private List<FichaDeTreino> fichaDeTreino;

}
