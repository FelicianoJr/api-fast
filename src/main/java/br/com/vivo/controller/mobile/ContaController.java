package br.com.vivo.controller.mobile;

import br.com.vivo.dto.ContaDto;
import br.com.vivo.model.Conta;
import br.com.vivo.service.ContaService;
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
@RequestMapping(value = "/api/v1/contas", produces = "application/json")
@Api(tags = {"Api", "Conta"})
public class ContaController extends MapperConverter<ContaDto, Conta> {

    @Autowired
    private ContaService contaService;

    @GetMapping("/pagamento/{cpf}")
    @ApiOperation(value = "Listar conta por cpf")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Conta recuperada com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Conta não encontrada"
            )})
    public List<ContaDto> obterConta(@PathVariable String cpf) {
        return convertToDto(contaService.buscarContas(cpf));
    }
}
