package api.workout.domains.entitys;

import api.workout.rest.request.RequestEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long id;

    @Column(name = "estado")
    private String estado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "numero")
    private String numero;

    @Column(name = "cep")
    private String cep;

    public Endereco(RequestEndereco requestEndereco) {
        this.id = requestEndereco.getId();
        this.estado = requestEndereco.getEstado();
        this.cidade = requestEndereco.getCidade();
        this.bairro = requestEndereco.getBairro();
        this.numero = requestEndereco.getNumero();
        this.cep = requestEndereco.getCep();
    }
}
