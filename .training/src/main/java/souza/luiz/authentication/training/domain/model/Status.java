package souza.luiz.authentication.training.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import souza.luiz.authentication.training.domain.dto.status.DadosCadastroStatus;

import java.util.List;

@Entity
@Table(name = "status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Status {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String status;

    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Task> task;

    private boolean ativo;

    public Status(String status) {
        this.status = status;
        this.ativo = true;
    }
}
