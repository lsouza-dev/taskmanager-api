package souza.luiz.authentication.training.domain.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
import souza.luiz.authentication.training.domain.dto.status.DadosDetalhamentoStatus;
import souza.luiz.authentication.training.domain.dto.status.DadosListagemStatus;
import souza.luiz.authentication.training.domain.repository.StatusRepository;
import souza.luiz.authentication.training.domain.service.StatusService;

@RestController
@RequestMapping("/status")
@SecurityRequirement(name = "bearer-key")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private StatusService statusService;

    @PostMapping("/name={statusName}")
    @Transactional
    public ResponseEntity cadastrar(@PathVariable String statusName, UriComponentsBuilder uriBuilder){
        var status = statusService.cadastrar(statusName);
        var uri = uriBuilder.path("/status/id={id}")
                .buildAndExpand(status.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoStatus(status));
    }

    @GetMapping("/id={idStatus}")
    public ResponseEntity detalhar(@RequestBody @Valid DadosDetalhamentoStatus dados){
        var status = statusRepository.findByStatus(dados.status());
        return ResponseEntity.ok().body(new DadosDetalhamentoStatus(status));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemStatus>> listar(@PageableDefault(sort = "id",size = 20)Pageable paginacao){
        var status = statusService.obterPageStatus(paginacao);
        return  ResponseEntity.ok().body(status);
    }
}
