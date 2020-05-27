package br.com.vivo.service;

import br.com.vivo.dto.CriarConsumoSaldoDto;
import br.com.vivo.model.Cdr;
import br.com.vivo.model.Formato;
import br.com.vivo.model.Produto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ProcessamentoSaldoService {

    @Autowired
    private CdrService cdrService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ConsumoParcialService consumoParcialService;

    public void registrar(String numeroProduto) {
        log.info("Processamento saldo");
        Produto produto = produtoService.buscarPorNumeroProduto(numeroProduto);
        List<Cdr> lista = cdrService.buscarPorOrigem(produto.getNumeroProduto());

        var qtdVoz = lista.stream()
                .filter(cdr -> cdr.getFormato().name().equals(Formato.VOZ.name()))
                .map(Cdr::getDuracao)
                .reduce(0, Integer::sum);

        var qtdDados = lista.stream()
                .filter(cdr -> cdr.getFormato().name().equals(Formato.DADOS.name()))
                .map(Cdr::getDuracao)
                .reduce(0, Integer::sum);

        Long qtdSms = lista.stream()
                .filter(cdr -> cdr.getFormato().name().equals(Formato.SMS.name()))
                .count();

        var mes = String.valueOf(LocalDateTime.now().getMonthValue());
        var ano = String.valueOf(LocalDateTime.now().getYear());

        Integer saldoDados = produto.getQtdDados() - qtdDados;
        Integer saldoSms = produto.getQtdSms() - qtdSms.intValue();
        Integer saldoVoz = produto.getQtdMinutos() - qtdVoz;

        CriarConsumoSaldoDto dto = new CriarConsumoSaldoDto();
        dto.setSaldoDados(saldoDados);
        dto.setSaldoMinutos(saldoVoz);
        dto.setSaldoSms(saldoSms);
        dto.setAnoReferencia(ano);
        dto.setMesReferencia(mes);
        dto.setIdProduto(produto.getId());

        consumoParcialService
                .buscarProdutoId(produto.getId())
                .stream()
                .filter(consumoSaldo ->
                        consumoSaldo.getMesReferencia().equals(mes)
                                && consumoSaldo.getAnoReferencia().equals(ano))
                .findFirst()
                .ifPresentOrElse(c -> consumoParcialService.atualizar(c.getId(), dto)
                        , () -> consumoParcialService.criar(dto));

    }
}
