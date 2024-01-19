package api.workout.domains.entitys;


import api.workout.rest.request.RequestAcademia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Academia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_academia_afiliada")
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

    public Academia(RequestAcademia acDTO) {
        this.id = acDTO.getId();
        this.academiaAfiliada = acDTO.getAcademiaAfiliada();
        this.cnpj = acDTO.getCnpj();
        this.endereco = new Endereco();
        this.aluno = new ArrayList<>();
        this.professor = new ArrayList<>();
        this.personal = new ArrayList<>();
        this.fichaDeTreino = new ArrayList<>();
    }
}
