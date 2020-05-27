package br.com.vivo.controller.mobile;

import br.com.vivo.dto.SaldoDto;
import br.com.vivo.model.Saldo;
import br.com.vivo.service.SaldoService;
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
@RequestMapping(value = "/api/v1/saldo", produces = "application/json")
@Api(tags = {"Api", "Saldo"})
public class SaldoController extends MapperConverter<SaldoDto, Saldo> {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/{cpf}")
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
    public List<SaldoDto> listarSaldo(@PathVariable String cpf) {
        return convertToDto(saldoService.buscarSaldoAtual(cpf));
    }
}
