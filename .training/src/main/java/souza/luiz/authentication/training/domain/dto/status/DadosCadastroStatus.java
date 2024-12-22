package souza.luiz.authentication.training.domain.dto.status;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroStatus(
        @NotBlank String status
) {
}
