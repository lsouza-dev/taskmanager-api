package souza.luiz.authentication.training.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import lombok.*;
import souza.luiz.authentication.training.domain.dto.task.DadosCadastroTask;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idPerfil")
    @JsonIgnoreProperties
    private Perfil perfil;

    private String title;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime conclusionTime;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;

    public Task(@Valid DadosCadastroTask dados) {
        this.title = dados.title();
        this.description = dados.description();
        this.createdTime = LocalDateTime.now();
        this.conclusionTime = dados.conclusionTime();
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", perfil=" + perfil +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdTime=" + createdTime +
                ", conclusionTime=" + conclusionTime +
                '}';
    }
}
