package souza.luiz.authentication.training.domain.dto.task;

import souza.luiz.authentication.training.domain.model.Task;

public record DadosListagemTask(
        Long id,
        String title,
        String description,
        String status
) {
    public DadosListagemTask(Task t){
        this(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus().getStatus()
        );
    }
}
