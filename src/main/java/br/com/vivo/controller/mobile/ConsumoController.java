package br.com.vivo.controller.mobile;

import br.com.vivo.dto.ConsumoSaldoDto;
import br.com.vivo.model.ConsumoSaldo;
import br.com.vivo.service.ConsumoParcialService;
import br.com.vivo.util.MapperConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/consumo", produces = "application/json")
@Api(tags = {"Api", "Consumo"})
public class ConsumoController extends MapperConverter<ConsumoSaldoDto, ConsumoSaldo> {

    @Autowired
    private ConsumoParcialService consumoParcialService;

    @GetMapping("/listar-saldo/{cpf}")
    @ApiOperation(value = "Listar saldo  atual por cpf")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Saldos recuperados com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Saldo não encontrado"
            )})
    public List<ConsumoSaldoDto> listarSaldo(@PathVariable String cpf) {
        return convertToDto(consumoParcialService.buscarSaldoAtual(cpf));
    }
}
