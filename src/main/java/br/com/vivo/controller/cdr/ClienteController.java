package br.com.vivo.controller.cdr;

import br.com.vivo.dto.AtualizarClienteDto;
import br.com.vivo.dto.ClienteDto;
import br.com.vivo.dto.CriarClienteDto;
import br.com.vivo.model.Cliente;
import br.com.vivo.service.ClienteService;
import br.com.vivo.util.MapperConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clientes", produces = "application/json")
@Api(tags = {"Api", "Clientes"})
public class ClienteController extends MapperConverter<ClienteDto, Cliente> {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ApiOperation(value = "Criar um novo cliente")
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Cliente cadastrado com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Cliente não encontrado."
            )})
    public ResponseEntity<Cliente> criar(@RequestBody CriarClienteDto criarClienteDto, UriComponentsBuilder ucBuilder) {
        final Cliente cliente = clienteService.criar(criarClienteDto);
        final UriComponents uriComponents = ucBuilder.path("/api/v1/clientes/{id}").buildAndExpand(cliente.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um cliente")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Cliente atualizado com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Cliente não encontrado."
            )})
    public ClienteDto atualizar(@PathVariable Long id, @RequestBody AtualizarClienteDto atualizarClienteDto) {
        return convertToDTo(clienteService.atualizar(id, atualizarClienteDto));
    }

    @GetMapping("/listar")
    @ApiOperation(value = "Listar todos os clientes")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Clientes recuperados com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Cliente(s) não encontrado(s)."
            )})
    public List<ClienteDto> buscarTodos() {
        return convertToDto(clienteService.buscarTodos());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remover um cliente")
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Cliente removido com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Cliente não encontrado."
            )})
    public ResponseEntity deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
