package souza.luiz.authentication.training.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import souza.luiz.authentication.training.domain.dto.status.DadosListagemStatus;
import souza.luiz.authentication.training.domain.model.Status;
import souza.luiz.authentication.training.domain.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public Status cadastrar(String dados) {
        var status = new Status(dados.toUpperCase());
        statusRepository.save(status);
        return status;
    }


    public Page<DadosListagemStatus> obterPageStatus(Pageable paginacao) {
        return statusRepository.findAllByAtivoTrue(paginacao).map(DadosListagemStatus::new);
    }
}
