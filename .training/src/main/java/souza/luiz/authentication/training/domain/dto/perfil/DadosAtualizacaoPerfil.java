package souza.luiz.authentication.training.domain.dto.perfil;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoPerfil(
        @NotBlank String nome) {
}
