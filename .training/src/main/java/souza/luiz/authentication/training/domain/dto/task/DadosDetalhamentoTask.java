package souza.luiz.authentication.training.domain.dto.task;

import souza.luiz.authentication.training.domain.model.Task;
import souza.luiz.authentication.training.domain.service.DataFormatterService;

public record DadosDetalhamentoTask(
        Long id,
        String title,
        String description,
        String createdTime,
        String conclusionTime,
        String status
) {
    public DadosDetalhamentoTask(Task task) {
        this(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                DataFormatterService.formatarData(task.getCreatedTime()),
                DataFormatterService.formatarData(task.getConclusionTime()),
                task.getStatus().getStatus()
        );
    }
}