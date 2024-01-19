package api.workout.domains.entitys;

import api.workout.domains.model.FichaDeTreinoDTO;
import api.workout.enums.Exercicio;
import api.workout.rest.request.RequestFichaDeTreino;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fichaDeTreino")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaDeTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha_de_treino")
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

    public FichaDeTreino(RequestFichaDeTreino requestFichaDeTreino) {
        this.id = requestFichaDeTreino.getId();
        this.exercicio = requestFichaDeTreino.getExercicio();
        this.professor = new Professor();
        this.aluno = new Aluno();
        this.academiaAfiliada = new Academia();
    }
}
