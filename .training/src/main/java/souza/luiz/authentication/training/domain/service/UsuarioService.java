package souza.luiz.authentication.training.domain.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import souza.luiz.authentication.training.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.authentication.training.domain.model.Perfil;
import souza.luiz.authentication.training.domain.model.Usuario;
import souza.luiz.authentication.training.domain.repository.UsuarioRepository;
import souza.luiz.authentication.training.infra.security.SecurityConfigurations;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityConfigurations securityConfigurations;

    public Usuario cadastrar(@Valid DadosCadastroUsuario dados) {
        var usuarioExistente = usuarioRepository.existsByLogin(dados.login());
        if(usuarioExistente) throw new DataIntegrityViolationException("Já existe um usuário com o email inserido.");
        var usuario = new Usuario(dados);
        usuario.setSenha(securityConfigurations.passwordEncoder().encode(dados.senha()));
        return usuario;
    }

    public Page<Usuario> listarUsuarios(Pageable page) {
        return usuarioRepository.findAll(page);
    }
}
