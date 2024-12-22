package souza.luiz.authentication.training.domain.dto.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import souza.luiz.authentication.training.domain.model.Perfil;

import java.time.LocalDateTime;

public record DadosCadastroTask(
        @NotNull Long idPerfil,
        @NotBlank String title,
        @NotBlank String description,
        @NotNull LocalDateTime conclusionTime
        ) {
}
