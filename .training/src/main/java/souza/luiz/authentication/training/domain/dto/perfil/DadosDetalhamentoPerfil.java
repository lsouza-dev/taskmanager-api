package souza.luiz.authentication.training.domain.dto.perfil;

import souza.luiz.authentication.training.domain.dto.task.DadosDetalhamentoTask;
import souza.luiz.authentication.training.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.authentication.training.domain.model.Perfil;

import java.util.List;

public record DadosDetalhamentoPerfil(
        String nome,
        DadosDetalhamentoUsuario usuario,
        int tasks
) {
    public DadosDetalhamentoPerfil(Perfil perfil) {
        this(
                perfil.getNome(),
                new DadosDetalhamentoUsuario(perfil.getUsuario()),
                perfil.getTasks().size()
        );
    }
}
