package api.workout.domains.entitys;

import api.workout.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "acesso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acesso")
    @PrimaryKeyJoinColumns(value = {@PrimaryKeyJoinColumn})
    private Long id;

    @Column(name = "nome_usuario")
    private String nomeUsuario;

    @Column(name = "senha_usuario")
    private String senha;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}