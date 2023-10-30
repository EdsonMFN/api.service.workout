package api.workout.domains.entitys;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "Academia")
@Table(name = "academia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Academia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_academia_afiliada")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "nome_academia_afiliada")
    private String academiaAfiliada;

    @OneToOne
    @JoinColumn(name = "id_endereco_afiliada",nullable = false)
    private Endereco endereco;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academiaAfiliada")
    private List<Aluno> aluno;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academiaAfiliada")
    private List<Professor> professor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academiaAfiliada")
    private List<Personal> personal;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academiaAfiliada")
    private List<FichaDeTreino> fichaDeTreino;
}
