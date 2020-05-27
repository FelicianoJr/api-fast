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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
    public void TesteDeletarPorIdCdrComSucesso() {

        doNothing().when(cdrRepository).deleteById(1L);

        cdrService.deletar(1L);

        verify(cdrRepository).deleteById(anyLong());
    }

    @Test
    public void TestarBuscarPorNumeroDoProdutoComSucesso() {

        CriarCdrDto criarCdrDto = new CriarCdrDto();
        criarCdrDto.setOrigem("71888579875");
        criarCdrDto.setDestino("7185859639");
        criarCdrDto.setFormato(Formato.VOZ);

        Cdr cdr = new Cdr(criarCdrDto);

        when(cdrRepository.findByOrigem("2343434333")).thenReturn(List.of(cdr));

        final List<Cdr> cdrs = cdrService.buscarPorOrigem("2343434333");

        assertEquals(1, cdrs.size());
        verify(cdrRepository).findByOrigem(anyString());
    }
}