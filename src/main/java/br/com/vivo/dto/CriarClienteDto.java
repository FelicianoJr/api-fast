package br.com.vivo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CriarClienteDto {

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("cpf")
	private String cpf;

	@JsonProperty("estado")
	private String estado;

	@JsonProperty("produtos")
	private List<CriarProdutoDto> produtos;

}
