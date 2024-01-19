package api.workout.domains.entitys;

import api.workout.rest.request.RequestProfessor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_professor")
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

    public Professor(RequestProfessor requestProfessor) {
        this.id = requestProfessor.getId();
        this.nome = requestProfessor.getNome();
        this.cpf = requestProfessor.getCpf();
        this.cref = requestProfessor.getCref();
        this.academiaAfiliada = new Academia();
        this.aluno = new ArrayList<>();
        this.fichaDeTreino = new ArrayList<>();
    }
}
