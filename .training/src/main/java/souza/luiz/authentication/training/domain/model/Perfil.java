package souza.luiz.authentication.training.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import souza.luiz.authentication.training.domain.dto.perfil.DadosAtualizacaoPerfil;
import souza.luiz.authentication.training.domain.dto.usuario.DadosCadastroUsuario;

import java.util.List;

@Entity
@Table(name = "perfis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Perfil(@Valid DadosCadastroUsuario dados) {
        this.nome = dados.nome();
    }

    public void atualizar(@Valid DadosAtualizacaoPerfil dados) {
        this.nome = dados.nome();
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
