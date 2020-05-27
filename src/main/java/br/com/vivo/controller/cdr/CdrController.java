package br.com.vivo.controller.cdr;

import br.com.vivo.dto.CriarCdrDto;
import br.com.vivo.stream.producer.ProducerCdrCreate;
import br.com.vivo.stream.producer.ProducerCdrDelete;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/cdr", produces = "application/json")
@Api(tags = {"Api", "Cdr"})
public class CdrController {

    @Autowired
    private ProducerCdrCreate producerCdrCreate;

    @Autowired
    private ProducerCdrDelete producerCdrDelete;

    @PostMapping
    @ApiOperation(value = "Registrar cdr, operação assincrona")
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Operação efetuado com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "cdr não encontrado."
            )})
    public ResponseEntity<Void> registrar(@RequestBody CriarCdrDto criarCdrDto) {
        producerCdrCreate.send(criarCdrDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar cdr, operação assincrona")
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "remoção concluida com sucesso"
            ),
            @ApiResponse(
                    code = 404,
                    message = "cdr não encontrado."
            )})
    public void deletar(@PathVariable String id) {
        producerCdrDelete.send(id);
    }
}
