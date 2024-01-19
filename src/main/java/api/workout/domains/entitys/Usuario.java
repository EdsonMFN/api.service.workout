package api.workout.domains.entitys;

import api.workout.enums.UserRole;
import api.workout.rest.request.RequestUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "acesso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public Usuario(RequestUsuario requestUsuario) {
        this.id = requestUsuario.getId();
        this.nomeUsuario = requestUsuario.getNomeUsuario();
        this.senha = requestUsuario.getSenha();
        this.role = requestUsuario.getRole();
    }
}