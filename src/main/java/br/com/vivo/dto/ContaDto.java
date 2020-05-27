package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ContaDto {

	@JsonProperty("numero")
	private Integer numero;

	@JsonProperty("mesReferencia")
	private LocalDate mesReferencia;

	@JsonProperty("periodoInicio")
	private LocalDate periodoInicio;

	@JsonProperty("periodoFim")
	private LocalDate periodoFim;

	@JsonProperty("dataEmissao")
	private LocalDateTime dataEmissao;

	@JsonProperty("valor")
	private BigDecimal valor;

}
