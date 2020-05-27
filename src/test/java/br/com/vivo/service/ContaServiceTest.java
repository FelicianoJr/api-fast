package br.com.vivo.service;

import br.com.vivo.dto.CriarContaDto;
import br.com.vivo.model.Conta;
import br.com.vivo.repository.ContaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ProdutoService produtoService;


    @Test
    @SuppressWarnings("unchecked")
    public void TestarCriarContaDoClienteComSucesso() {

        CriarContaDto criarContaDto = new CriarContaDto();
        criarContaDto.setValor(new BigDecimal(100.00));
        criarContaDto.setIdProduto(1L);

        contaService.criar(criarContaDto);

        ArgumentCaptor<Conta> argumentCaptor = ArgumentCaptor.forClass(Conta.class);

        verify(contaRepository).save(argumentCaptor.capture());

        assertEquals(new BigDecimal("100"), argumentCaptor.getValue().getValor());
    }

    @Test
    public void TestarBuscarPorPeriodoComSucesso() {

        given(contaRepository.findByPeriodoFimEqualsAndValorIsNull(any())).willReturn(List.of(new Conta()));

        List retorno = contaService.buscarContasComFechamentoParaDataHoje();
        assertEquals(1, retorno.size());
    }

    @Test
    public void TestarBUscarPorIdComSucesso() {

        given(contaRepository.findById(1L)).willReturn(Optional.ofNullable(new Conta()));
        Conta conta = contaService.buscarPorId(1L);

        assertNotNull(conta);
    }
}