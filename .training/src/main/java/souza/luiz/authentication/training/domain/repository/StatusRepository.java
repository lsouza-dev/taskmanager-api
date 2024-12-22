package souza.luiz.authentication.training.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import souza.luiz.authentication.training.domain.model.Status;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {
    Status findByStatus(String status);

    Page<Status> findAllByAtivoTrue(Pageable paginacao);
}
