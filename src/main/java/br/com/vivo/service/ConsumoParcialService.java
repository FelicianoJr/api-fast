package br.com.vivo.service;

import br.com.vivo.dto.CriarConsumoSaldoDto;
import br.com.vivo.exception.NaoEncontradoException;
import br.com.vivo.model.Cliente;
import br.com.vivo.model.ConsumoSaldo;
import br.com.vivo.model.Produto;
import br.com.vivo.repository.ConsumoParcialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumoParcialService {

    @Autowired
    private ConsumoParcialRepository consumoParcialRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @CacheEvict(cacheNames = "ConsumoContaParcial", allEntries = true)
    public ConsumoSaldo criar(CriarConsumoSaldoDto dto) {
        Produto produto = produtoService.buscarPorId(dto.getIdProduto());
        ConsumoSaldo consumoSaldo = new ConsumoSaldo();
        consumoSaldo.atualizar(dto, produto);
        return consumoParcialRepository.save(consumoSaldo);
    }

    public ConsumoSaldo atualizar(Long id, CriarConsumoSaldoDto dto) {
        Produto produto = produtoService.buscarPorId(dto.getIdProduto());
        final ConsumoSaldo consumoSaldo = buscarPorId(id);
        consumoSaldo.atualizar(dto, produto);
        return consumoParcialRepository.save(consumoSaldo);
    }

    public List<ConsumoSaldo> buscarProdutoId(Long id) {
        return consumoParcialRepository.findByProduto(id);
    }

    public ConsumoSaldo buscarPorId(Long id) {
        return consumoParcialRepository.findById(id).orElseThrow(NaoEncontradoException::new);
    }

    @Cacheable(cacheNames = "ConsumoContaParcial", key = "#root.todos")
    public List<ConsumoSaldo> buscarTodosPorId(List<Long> ids) {
        return consumoParcialRepository.findAllById(ids);
    }

    public List<ConsumoSaldo> buscarSaldoAtual(String cpf) {

        final Cliente cliente = clienteService.buscarPorCpfNumeroProduto(cpf);
        final List<ConsumoSaldo> consumoSaldos = new ArrayList<>();

        var mesAtual = String.valueOf(LocalDateTime.now().getMonthValue());
        var anoAtual = String.valueOf(LocalDateTime.now().getYear());

        cliente.getProdutos()
                .forEach(produto -> {
                    produto.getConsumoContaParciais()
                            .stream()
                            .filter(s -> s.getAnoReferencia().equals(anoAtual) && s.getMesReferencia().equals(mesAtual))
                            .forEach(consumoSaldo -> consumoSaldos.add(consumoSaldo));
                });

        return consumoSaldos;
    }
}
