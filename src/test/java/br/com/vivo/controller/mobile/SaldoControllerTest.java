package br.com.vivo.controller.mobile;

import br.com.vivo.model.Saldo;
import br.com.vivo.service.SaldoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SaldoController.class)
public class SaldoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaldoService saldoService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testeControllerBuscarSaldo() throws Exception {

        when(saldoService.buscarSaldoAtual(any())).thenReturn(List.of(new Saldo()));

        this.mockMvc.perform(get("/api/v1/saldo/66871745889")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"numero\":\"555\", \"valor\":\"500\",\"mesReferencia\":\"05\" }"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}