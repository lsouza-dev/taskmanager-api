package souza.luiz.authentication.training.domain.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotBlank String login,
        @NotBlank String senha
) {
}
