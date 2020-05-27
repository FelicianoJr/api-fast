package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SaldoDto {

	@JsonProperty("saldoDados")
	private Integer saldoDados;

	@JsonProperty("saldoSms")
	private Integer saldoSms;

	@JsonProperty("saldoMinutos")
	private Integer saldoMinutos;

	@JsonProperty("mesReferencia")
	private String mesReferencia;

	@JsonProperty("anoReferencia")
	private String anoReferencia;

}
