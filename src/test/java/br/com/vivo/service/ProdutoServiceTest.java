package br.com.vivo.service;

import br.com.vivo.dto.CriarProdutoDto;
import br.com.vivo.model.Cliente;
import br.com.vivo.model.Produto;
import br.com.vivo.repository.ProdutoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void TestarBuscarPorIdDoProdutoComSucesso() {

        CriarProdutoDto criarProdutoDto = new CriarProdutoDto();
        criarProdutoDto.setNumeroProduto("234343");

        final Produto produto = new Produto(criarProdutoDto, new Cliente());
        Optional optionalProduto = java.util.Optional.ofNullable(produto);
        when(produtoRepository.findById(1L)).thenReturn(optionalProduto);

        Produto resultado = produtoService.buscarPorId(1L);

        assertEquals("234343", resultado.getNumeroProduto());
        verify(produtoRepository).findById(anyLong());
    }

    @Test
    public void TestarBuscarPorNumeroDoProdutoComSucesso() {

        CriarProdutoDto criarProdutoDto = new CriarProdutoDto();
        criarProdutoDto.setNumeroProduto("234343");

        final Produto produto = new Produto(criarProdutoDto, new Cliente());
        Optional optionalProduto = java.util.Optional.ofNullable(produto);
        when(produtoRepository.findByNumeroProduto("234343")).thenReturn(optionalProduto);

        Produto resultado = produtoService.buscarPorNumeroProduto("234343");

        assertEquals("234343", resultado.getNumeroProduto());
        verify(produtoRepository).findByNumeroProduto(anyString());
    }
}