package br.com.vivo.service;

import br.com.vivo.dto.CriarSaldoDto;
import br.com.vivo.exception.NaoEncontradoException;
import br.com.vivo.model.Cliente;
import br.com.vivo.model.Produto;
import br.com.vivo.model.Saldo;
import br.com.vivo.repository.SaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaldoService {

    @Autowired
    private SaldoRepository saldoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @CacheEvict(cacheNames = "ConsumoContaParcial", allEntries = true)
    public Saldo criar(CriarSaldoDto dto) {
        Produto produto = produtoService.buscarPorId(dto.getIdProduto());
        Saldo saldo = new Saldo();
        saldo.atualizar(dto, produto);
        return saldoRepository.save(saldo);
    }

    public Saldo atualizar(Long id, CriarSaldoDto dto) {
        Produto produto = produtoService.buscarPorId(dto.getIdProduto());
        final Saldo saldo = buscarPorId(id);
        saldo.atualizar(dto, produto);
        return saldoRepository.save(saldo);
    }

    public List<Saldo> buscarProdutoId(Long id) {
        return saldoRepository.findByProduto(id);
    }

    public Saldo buscarPorId(Long id) {
        return saldoRepository.findById(id).orElseThrow(NaoEncontradoException::new);
    }

    @Cacheable(cacheNames = "ConsumoContaParcial", key = "#root.todos")
    public List<Saldo> buscarTodosPorId(List<Long> ids) {
        return saldoRepository.findAllById(ids);
    }

    public List<Saldo> buscarSaldoAtual(String cpf) {

        final Cliente cliente = clienteService.buscarPorCpfNumeroProduto(cpf);
        final List<Saldo> saldos = new ArrayList<>();

        var mesAtual = String.valueOf(LocalDateTime.now().getMonthValue());
        var anoAtual = String.valueOf(LocalDateTime.now().getYear());

        cliente.getProdutos()
                .forEach(produto -> {
                    produto.getConsumoContaParciais()
                            .stream()
                            .filter(s -> s.getAnoReferencia().equals(anoAtual) && s.getMesReferencia().equals(mesAtual))
                            .forEach(consumoSaldo -> saldos.add(consumoSaldo));
                });

        return saldos;
    }
}
