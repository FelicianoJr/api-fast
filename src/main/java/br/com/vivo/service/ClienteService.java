package br.com.vivo.service;

import br.com.vivo.dto.AtualizarClienteDto;
import br.com.vivo.dto.CriarClienteDto;
import br.com.vivo.dto.CriarContaDto;
import br.com.vivo.exception.NaoEncontradoException;
import br.com.vivo.model.Cliente;
import br.com.vivo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaService contaService;

    public Cliente criar(CriarClienteDto dto) {
        Cliente cliente = new Cliente(dto);

        clienteRepository.save(cliente);

        IniciarContaCliente(cliente);

        return cliente;
    }

    private void IniciarContaCliente(Cliente cliente) {
        CriarContaDto criarContaDto = new CriarContaDto();
        var dataApos30dias = LocalDate.now().plusDays(30);

        cliente.getProdutos()
                .stream()
                .findFirst()
                .ifPresent(produto -> {
                    criarContaDto.setIdProduto(produto.getId());
                    criarContaDto.setPeriodoInicio(LocalDate.now());
                    criarContaDto.setPeriodoFim(dataApos30dias);
                    criarContaDto.setMesReferencia(LocalDate.now());
                    contaService.criar(criarContaDto);
                });
    }

    public Cliente atualizar(Long id, AtualizarClienteDto dto) {
        final Cliente cliente = buscarPorId(id);
        cliente.atualizar(dto);
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(NaoEncontradoException::new);
    }

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorCpfNumeroProduto(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(NaoEncontradoException::new);
    }
}
