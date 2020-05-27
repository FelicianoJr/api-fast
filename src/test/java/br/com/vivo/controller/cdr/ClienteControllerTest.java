package br.com.vivo.controller.cdr;

import br.com.vivo.dto.AtualizarClienteDto;
import br.com.vivo.model.Cliente;
import br.com.vivo.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void TesteControllerCriarClienteComSucesso() throws Exception {

        when(clienteService.criar(any())).thenReturn(new Cliente());

        this.mockMvc.perform(post("/api/v1/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"teste\", \"cpf\":\"10010052055\",\"estado\":\"PB\" }"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void TesteControllerAtualizarClienteComSucesso() throws Exception {

        given(
                clienteService.atualizar(any(Long.class), any(AtualizarClienteDto.class))
        ).willReturn(new Cliente());

        this.mockMvc.perform(put("/api/v1/clientes/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"teste\", \"cpf\":\"10010052055\",\"estado\":\"PB\" }"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void TesteControllerBuscarTodosCleintesComSucesso() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        when(clienteService.buscarTodos()).thenReturn(clientes);

        this.mockMvc.perform(get("/api/v1/clientes/listar")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}