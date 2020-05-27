package br.com.vivo.service;

import br.com.vivo.dto.CriarContaDto;
import br.com.vivo.dto.FecharValoresContaDto;
import br.com.vivo.model.Conta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class FechamentoContaService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ProdutoService produtoService;

    public void fecharConta() {
        log.info("Fechamento conta");

        final List<Conta> contasComFechamentoParaDataHoje = contaService.buscarContasComFechamentoParaDataHoje();

        contasComFechamentoParaDataHoje.forEach(conta ->{
            contaService.atualizar(conta.getId(), getFecharValoresPadraoConta(conta));
            iniciarNovoCicloConta(conta);
        });
    }

    private FecharValoresContaDto getFecharValoresPadraoConta(Conta conta) {
        final FecharValoresContaDto dto = new FecharValoresContaDto();
        dto.setNumero(777);
        dto.setValor(new BigDecimal(99.00));
        dto.setDataEmissao(LocalDateTime.now());
        dto.setIdProduto(conta.getProduto().getId());
        return dto;
    }

    private void iniciarNovoCicloConta(Conta conta) {
        CriarContaDto criarContaDto = new CriarContaDto();
        var dataApos30dias = LocalDate.now().plusDays(30);
        criarContaDto.setIdProduto(conta.getProduto().getId());
        criarContaDto.setPeriodoInicio(LocalDate.now());
        criarContaDto.setPeriodoFim(dataApos30dias);
        criarContaDto.setMesReferencia(LocalDate.now());
        contaService.criar(criarContaDto);
    }
}
