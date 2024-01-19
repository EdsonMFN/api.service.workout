package api.workout.domains.entitys;

import api.workout.rest.request.RequestAluno;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno",insertable=false, updatable=false)
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

    public Aluno(RequestAluno requestAluno) {
        this.nome = requestAluno.getNome();
        this.cpf = requestAluno.getCpf();
        this.academiaAfiliada = new Academia();
        this.professor = new Professor();
        this.personal = new Personal();
        this.fichaDeTreino = new ArrayList<>();
    }
}
