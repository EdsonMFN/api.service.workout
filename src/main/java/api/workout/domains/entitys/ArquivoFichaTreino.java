package api.workout.domains.entitys;

import api.workout.enums.TipoDeArquivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BaixarTreino")
@Table(name = "baixarTreino")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArquivoFichaTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Baixar_Treino")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_Ficha_De_Treino")
    private FichaDeTreino fichaDeTreino;

    @Column(name = "Tipo_De_Arquivo")
    @Enumerated(EnumType.STRING)
    private TipoDeArquivo tipoDeArquivo;
}
