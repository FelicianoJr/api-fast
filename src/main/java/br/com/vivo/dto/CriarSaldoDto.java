package br.com.vivo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CriarSaldoDto implements Serializable {

    private Integer saldoDados;

    private Integer saldoSms;

    private Integer saldoMinutos;

    private String mesReferencia;

    private String anoReferencia;

    private Long idProduto;
}
