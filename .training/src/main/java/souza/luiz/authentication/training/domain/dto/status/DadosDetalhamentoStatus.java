package souza.luiz.authentication.training.domain.dto.status;

import souza.luiz.authentication.training.domain.model.Status;

public record DadosDetalhamentoStatus(
        String status
) {
    public DadosDetalhamentoStatus(Status status) {
        this(
                status.getStatus()
        );
    }
}
