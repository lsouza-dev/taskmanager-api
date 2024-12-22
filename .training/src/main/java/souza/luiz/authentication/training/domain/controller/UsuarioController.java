package souza.luiz.authentication.training.domain.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import souza.luiz.authentication.training.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.authentication.training.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.authentication.training.domain.repository.UsuarioRepository;
import souza.luiz.authentication.training.domain.service.PerfilService;
import souza.luiz.authentication.training.domain.service.UsuarioService;

import java.util.ListIterator;


@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = usuarioService.cadastrar(dados);
        var perfil = perfilService.cadastrar(dados);

        usuario.setPerfil(perfil);
        perfil.setUsuario(usuario);

        usuarioRepository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoUsuario>> listarUsuarios(Pageable page){
        var usuarios = usuarioService.listarUsuarios(page).map(DadosDetalhamentoUsuario::new);
        return ResponseEntity.ok().body(usuarios);
    }

    @DeleteMapping("/id={id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        var usuario = usuarioRepository.getReferenceById(id);
        usuario.desativar();
        return ResponseEntity.noContent().build();
    }

}
