package br.com.vivo.controller.cdr;

import br.com.vivo.stream.producer.ProducerCdrCriacao;
import br.com.vivo.stream.producer.ProducerCdrDelecao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CdrController.class)
public class CdrControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerCdrCriacao producerCdrCriacao;

    @MockBean
    private ProducerCdrDelecao producerCdrDelecao;

    @Test
    public void testeControllerCriarCdrComSucesso() throws Exception {

        doNothing().when(producerCdrCriacao).send(any());

        this.mockMvc.perform(post("/api/v1/cdr")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testeControllerDeletarCdrComSucesso() throws Exception {

        doNothing().when(producerCdrDelecao).send(any());

        this.mockMvc.perform(delete("/api/v1/cdr/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

}