package br.com.vivo.dto;

import lombok.Data;

@Data
public class CriarConsumoSaldoDto {

    private Integer saldoDados;

    private Integer saldoSms;

    private Integer saldoMinutos;

    private String mesReferencia;

    private String anoReferencia;

    private Long idProduto;
}
