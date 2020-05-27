package br.com.vivo.service;

import br.com.vivo.dto.CriarCdrDto;
import br.com.vivo.model.Cdr;
import br.com.vivo.repository.CdrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdrService {

    @Autowired
    private CdrRepository cdrRepository;

    public Cdr criar(CriarCdrDto criarCdrDto) {
        final Cdr cdr = new Cdr(criarCdrDto);
        return cdrRepository.save(cdr);
    }

    public void deletar(Long id) {
        cdrRepository.deleteById(id);
    }

    public List<Cdr> buscarPorOrigem(String origem) {
        return cdrRepository.findByOrigem(origem);
    }

}
