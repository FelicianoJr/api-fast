package br.com.vivo.model;

import br.com.vivo.dto.CriarContaDto;
import br.com.vivo.dto.FecharValoresContaDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "conta")
@EqualsAndHashCode(of = { "id" })
public class Conta implements Serializable {

	private static final long serialVersionUID = 3197769213606321084L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "numero")
	private Integer numero;

	@Column(name = "mes_referencia")
	private LocalDate mesReferencia;

	@Column(name = "periodo_inicio")
	private LocalDate periodoInicio;

	@Column(name = "periodo_fim")
	private LocalDate periodoFim;

	@Column(name = "data_emissao")
	private LocalDateTime dataEmissao;

	@Column(name = "valor")
	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public Conta atualizar(CriarContaDto criarContaDto, Produto produto) {
		this.numero = criarContaDto.getNumero();
		this.mesReferencia = criarContaDto.getMesReferencia();
		this.periodoInicio = criarContaDto.getPeriodoInicio();
		this.periodoFim = criarContaDto.getPeriodoFim();
		this.dataEmissao = criarContaDto.getDataEmissao();
		this.valor = criarContaDto.getValor();
		this.produto = produto;
		return this;
	}

	public Conta atualizarFechamento(FecharValoresContaDto dto, Produto produto) {
		this.numero = dto.getNumero();
		this.dataEmissao = dto.getDataEmissao();
		this.valor = dto.getValor();
		this.produto = produto;
		return this;
	}
}
