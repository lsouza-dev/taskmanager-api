package souza.luiz.authentication.training.domain.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import souza.luiz.authentication.training.domain.dto.perfil.DadosAtualizacaoPerfil;
import souza.luiz.authentication.training.domain.dto.perfil.DadosDetalhamentoPerfil;
import souza.luiz.authentication.training.domain.dto.perfil.DadosListagemPerfil;
import souza.luiz.authentication.training.domain.repository.PerfilRepository;
import souza.luiz.authentication.training.domain.repository.UsuarioRepository;
import souza.luiz.authentication.training.domain.service.PerfilService;

@RestController
@RequestMapping("/perfis")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilService perfilService;


    @GetMapping
    public ResponseEntity<Page<DadosListagemPerfil>> listar(Pageable page){
        var perfis = perfilService.listar(page).map(DadosListagemPerfil::new);
        return ResponseEntity.ok().body(perfis);
    }

    @GetMapping("id={id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var perfil = perfilRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DadosDetalhamentoPerfil(perfil));
    }

    @PutMapping("/id={id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPerfil dados){
        var perfil = perfilRepository.getReferenceById(id);
        perfil.atualizar(dados);
        return ResponseEntity.ok().body(new DadosDetalhamentoPerfil(perfil));
    }

}
