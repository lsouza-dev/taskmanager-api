package souza.luiz.authentication.training.domain.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import souza.luiz.authentication.training.domain.dto.usuario.DadosCadastroUsuario;
import souza.luiz.authentication.training.domain.model.Perfil;
import souza.luiz.authentication.training.domain.repository.PerfilRepository;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public Perfil cadastrar(@Valid DadosCadastroUsuario dados){
        return new Perfil(dados);
    }

    public Page<Perfil> listar(Pageable page) {
        return perfilRepository.obterUsuariosAtivos(page);
    }

}
