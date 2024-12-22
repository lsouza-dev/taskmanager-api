package souza.luiz.authentication.training.domain.dto.status;

import souza.luiz.authentication.training.domain.model.Status;

public record DadosListagemStatus(
        Long id,
        String status
) {

    public DadosListagemStatus(Status s){
        this(
                s.getId(),
                s.getStatus()
        );
    }
}
