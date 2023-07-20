package Projeto.Academia.entitys.fichaDeTreino;

import Projeto.Academia.entitys.academia.Academia;
import Projeto.Academia.entitys.aluno.Aluno;
import Projeto.Academia.entitys.personal.Personal;
import Projeto.Academia.entitys.professor.Professor;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "FichaDeTreino")
@Table(name = "fichaDeTreino")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class FichaDeTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha_de_treino")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "ficha_de_exercicio")
    @Enumerated(EnumType.STRING)
    private Exercicio exercicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cref_professor")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_academia_afiliada")
    private Academia academiaAfiliada;
}
