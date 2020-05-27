package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AtualizarProdutoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY,value = "id")
    private Long id;

    @JsonProperty("numeroProduto")
    private String numeroProduto;

    @JsonProperty("dataHabilitacao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataHabilitacao;

    @JsonProperty("plano")
    private String plano;

    @JsonProperty("qtdDados")
    private Integer qtdDados;

    @JsonProperty("qtdSms")
    private Integer qtdSms;

    @JsonProperty("qtdMinutos")
    private Integer qtdMinutos;

}
