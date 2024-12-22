package souza.luiz.authentication.training.domain.dto.perfil;

import souza.luiz.authentication.training.domain.dto.usuario.DadosDetalhamentoUsuario;
import souza.luiz.authentication.training.domain.model.Perfil;

public record DadosListagemPerfil(
        String nome,
        DadosDetalhamentoUsuario usuario
) {

    public DadosListagemPerfil(Perfil p){
        this(
                p.getNome(),
                new DadosDetalhamentoUsuario(p.getUsuario())
        );
    }
}
