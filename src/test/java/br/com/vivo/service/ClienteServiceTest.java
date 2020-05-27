package br.com.vivo.service;

import br.com.vivo.dto.CriarClienteDto;
import br.com.vivo.dto.CriarProdutoDto;
import br.com.vivo.model.Cliente;
import br.com.vivo.repository.ClienteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;
    private CriarClienteDto criarClienteDto;

    @Mock
    private ContaService contaService;

    @Before
    public void setup() {
        criarCliente();
    }

    private void criarCliente() {
        CriarProdutoDto criarProdutoDto = new CriarProdutoDto();
        criarProdutoDto.setDataHabilitacao(LocalDateTime.now());
        criarProdutoDto.setNumeroProduto("71885697852");
        criarProdutoDto.setPlano("max");
        criarProdutoDto.setQtdDados(500);
        criarProdutoDto.setQtdMinutos(1000);
        criarProdutoDto.setQtdSms(500);

        var listaProdutos = List.of(criarProdutoDto);

        this.criarClienteDto = new CriarClienteDto();
        criarClienteDto.setCpf("54578787787");
        criarClienteDto.setEstado("PB");
        criarClienteDto.setNome("Francisco xavier");
        criarClienteDto.setProdutos(listaProdutos);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void TestarCriacaoDoClienteComSucesso() {

        clienteService.criar(this.criarClienteDto);

        ArgumentCaptor<Cliente> argumentCaptor = ArgumentCaptor.forClass(Cliente.class);

        verify(clienteRepository).save(argumentCaptor.capture());

        assertEquals(1, argumentCaptor.getValue().getProdutos().size());
        assertEquals("Francisco xavier", argumentCaptor.getValue().getNome());

    }

    @Test
    public void TestarBuscarTodosDoClienteComSucesso() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);

        final List<Cliente> resultado = clienteService.buscarTodos();

        assertEquals(1, resultado.size());
        verify(clienteRepository).findAll();
    }

    @Test
    public void TestarBuscarPorIdDoClienteComSucesso() {

        final Cliente cliente = new Cliente(criarClienteDto);
        Optional optionalBills = java.util.Optional.ofNullable(cliente);
        when(clienteRepository.findById(1L)).thenReturn(optionalBills);

        Cliente result = clienteService.buscarPorId(1L);

        assertEquals("54578787787", result.getCpf());
        verify(clienteRepository).findById(anyLong());
    }


    @Test
    public void TestaDeletarPorIdDoClienteComSucesso() {

        doNothing().when(clienteRepository).deleteById(1L);

        clienteService.deletar(1L);

        verify(clienteRepository).deleteById(anyLong());
    }

}