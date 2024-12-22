package souza.luiz.authentication.training.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import souza.luiz.authentication.training.domain.model.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Long> {

    @Query("""
            SELECT t FROM Task t
            JOIN t.status s
            WHERE t.title = :title
            AND
            
            """)
    Task pesquisarTaskComTituloExistenteNaoFinalizada(String title);
}
