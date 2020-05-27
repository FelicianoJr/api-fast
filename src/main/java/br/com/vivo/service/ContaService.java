package br.com.vivo.service;

import br.com.vivo.dto.CriarContaDto;
import br.com.vivo.dto.FecharValoresContaDto;
import br.com.vivo.exception.NaoEncontradoException;
import br.com.vivo.model.Cliente;
import br.com.vivo.model.Conta;
import br.com.vivo.model.Produto;
import br.com.vivo.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    public Conta criar(CriarContaDto criarContaDto) {
        Produto produto = produtoService.buscarPorId(criarContaDto.getIdProduto());
        Conta conta = new Conta();
        conta.atualizar(criarContaDto, produto);
        return contaRepository.save(conta);
    }

    public Conta atualizar(Long id, FecharValoresContaDto fecharValoresContaDto) {
        Produto produto = produtoService.buscarPorId(fecharValoresContaDto.getIdProduto());
        System.out.println(produto.getId());
        Conta conta = buscarPorId(id);
        conta.atualizarFechamento(fecharValoresContaDto, produto);
        return contaRepository.save(conta);
    }

    public List<Conta> buscarContasComFechamentoParaDataHoje() {
        return contaRepository.findByPeriodoFimEqualsAndValorIsNull(LocalDate.now());
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(NaoEncontradoException::new);
    }

    public List<Conta> buscarContas(String cpf) {

        Cliente cliente = clienteService.buscarPorCpfNumeroProduto(cpf);
        List<Conta> contas = new ArrayList<>();

        cliente.getProdutos()
                .forEach(produto -> {
                    produto.getConta()
                            .stream()
                            .filter(conta -> conta.getValor() != null)
                            .max(Comparator.comparing(Conta::getId))
                            .ifPresent(conta -> contas.add(conta));
                });

        return contas;
    }

}
