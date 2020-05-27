package br.com.vivo.service;

import br.com.vivo.dto.CriarCdrDto;
import br.com.vivo.model.Cdr;
import br.com.vivo.model.Formato;
import br.com.vivo.repository.CdrRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CdrServiceTest {

    @Mock
    private CdrRepository cdrRepository;

    @InjectMocks
    private CdrService cdrService;

    @Test
    public void TestarCriarCdrComSucesso() {

        final CriarCdrDto criarCdrDto = new CriarCdrDto();
        criarCdrDto.setDataRegistro(LocalDateTime.now());
        criarCdrDto.setDestino("74855899855");
        criarCdrDto.setDuracao(5);
        criarCdrDto.setFormato(Formato.VOZ);
        criarCdrDto.setOrigem("87878452454");

        cdrService.criar(criarCdrDto);

        ArgumentCaptor<Cdr> argumentCaptor = ArgumentCaptor.forClass(Cdr.class);

        verify(cdrRepository).save(argumentCaptor.capture());

        assertEquals(Formato.VOZ, argumentCaptor.getValue().getFormato());
        assertEquals("74855899855", argumentCaptor.getValue().getDestino());

    }

    @Test
    public void TestaDeletarPorIdCdrComSucesso() {

        doNothing().when(cdrRepository).deleteById(1L);

        cdrService.deletar(1L);

        verify(cdrRepository).deleteById(anyLong());
    }
}