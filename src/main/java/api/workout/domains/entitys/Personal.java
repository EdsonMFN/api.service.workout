package api.workout.domains.entitys;

import api.workout.domains.entitys.Academia;
import api.workout.domains.entitys.Aluno;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Persoanl")
@Table(name = "personal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personal")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personal")
    private List<Aluno> aluno;
}
