package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProdutoDto {

    @JsonProperty("numeroProduto")
    private String numeroProduto;

    @JsonProperty("dataHabilitacao")
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
