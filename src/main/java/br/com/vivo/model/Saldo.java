package br.com.vivo.model;

import br.com.vivo.dto.CriarSaldoDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "saldo")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Saldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saldo_dados")
    private Integer saldoDados;

    @Column(name = "saldo_sms")
    private Integer saldoSms;

    @Column(name = "saldo_minutos")
    private Integer saldoMinutos;

    @Column(name = "mes_referencia")
    private String mesReferencia;

    @Column(name = "ano_referencia")
    private String anoReferencia;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @PrePersist
    private void prePersist() {
        this.dataRegistro = LocalDateTime.now();
    }

    public Saldo atualizar(CriarSaldoDto dto, Produto produto) {
        this.saldoDados = dto.getSaldoDados();
        this.saldoSms = dto.getSaldoSms();
        this.saldoMinutos = dto.getSaldoMinutos();
        this.mesReferencia = dto.getMesReferencia();
        this.anoReferencia = dto.getAnoReferencia();
        this.produto = produto;
        return this;
    }

}
