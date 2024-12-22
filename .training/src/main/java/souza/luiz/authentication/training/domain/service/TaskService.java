package souza.luiz.authentication.training.domain.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import souza.luiz.authentication.training.domain.dto.task.DadosCadastroTask;
import souza.luiz.authentication.training.domain.dto.task.DadosListagemTask;
import souza.luiz.authentication.training.domain.model.Task;
import souza.luiz.authentication.training.domain.repository.PerfilRepository;
import souza.luiz.authentication.training.domain.repository.StatusRepository;
import souza.luiz.authentication.training.domain.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private StatusRepository statusRepository;

    public Task cadastrar(@Valid DadosCadastroTask dados) {
        var perfil = perfilRepository.getReferenceById(dados.idPerfil());
        var status = statusRepository.getReferenceById(1L);
        var task = new Task(dados);
        task.setPerfil(perfil);
        task.setStatus(status);
        return task;
    }

    public Page<DadosListagemTask> obterPageTasks(Pageable paginacao) {
        return taskRepository.findAll(paginacao).map(DadosListagemTask::new);
    }
}
