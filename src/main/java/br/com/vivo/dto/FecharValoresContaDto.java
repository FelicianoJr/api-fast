package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FecharValoresContaDto {

	@JsonProperty("numero")
	private Integer numero;

	@JsonProperty("dataEmissao")
	private LocalDateTime dataEmissao;

	@JsonProperty("valor")
	private BigDecimal valor;

	@JsonProperty("idProduto")
	private Long idProduto;
}
