package souza.luiz.authentication.training.domain.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import souza.luiz.authentication.training.domain.dto.task.DadosCadastroTask;
import souza.luiz.authentication.training.domain.dto.task.DadosDetalhamentoTask;
import souza.luiz.authentication.training.domain.dto.task.DadosListagemTask;
import souza.luiz.authentication.training.domain.repository.PerfilRepository;
import souza.luiz.authentication.training.domain.repository.TaskRepository;
import souza.luiz.authentication.training.domain.service.TaskService;

@RestController
@RequestMapping("/tasks")
@SecurityRequirement(name = "bearer-key")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private TaskService taskService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar (@RequestBody @Valid DadosCadastroTask dados, UriComponentsBuilder uriBuilder){
        var task = taskService.cadastrar(dados);
        System.out.println(task);
        taskRepository.save(task);
        var uri = uriBuilder.path("/task/id={id}")
                .buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTask(task));
    }

    @GetMapping("/id={idTask}")
    public ResponseEntity detalhar(@PathVariable Long idTask){
        var task = taskRepository.getReferenceById(idTask);
        return ResponseEntity.ok().body(new DadosDetalhamentoTask(task));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTask>> listar(@PageableDefault(sort = "id",size = 20)Pageable paginacao){
        var tasks = taskService.obterPageTasks(paginacao);
        return ResponseEntity.ok().body(tasks);
    }
}
