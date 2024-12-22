package souza.luiz.authentication.training.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import souza.luiz.authentication.training.domain.model.Perfil;

import java.util.List;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {

    @Query("""
            SELECT p FROM Perfil p
            JOIN p.usuario u
            WHERE u.ativo = true
            """)
    Page<Perfil> obterUsuariosAtivos(Pageable page);
}
