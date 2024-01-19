package api.workout.domains.entitys;

import api.workout.rest.request.RequestPersonal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    public Personal(RequestPersonal requestPersonal) {
        this.id = requestPersonal.getId();
        this.nome = requestPersonal.getNome();
        this.cpf = requestPersonal.getCpf();
        this.cref = requestPersonal.getCref();
        this.academiaAfiliada = new Academia();
        this.aluno = new ArrayList<>();
    }
}
