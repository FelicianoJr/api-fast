package br.com.vivo.model;

import br.com.vivo.dto.AtualizarProdutoDto;
import br.com.vivo.dto.CriarProdutoDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "produto")
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Produto implements Serializable {

	private static final long serialVersionUID = -8486635463318132883L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "numero_produto", nullable = false, unique = true)
	private String numeroProduto;

	@Column(name = "data_habilitacao", nullable = false)
	private LocalDateTime dataHabilitacao;

	@Column(name = "plano", nullable = false)
	private String plano;

	@Column(name = "qtd_dados", nullable = false)
	private Integer qtdDados;

	@Column(name = "qtd_sms", nullable = false)
	private Integer qtdSms;

	@Column(name = "qtd_minutos", nullable = false)
	private Integer qtdMinutos;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "produto")
	private List<Conta> conta;

	@OneToMany(mappedBy = "produto")
	private List<Saldo> consumoContaParciais;

	public Produto(CriarProdutoDto dto, Cliente cliente) {
		this.cliente = cliente;
		this.numeroProduto = dto.getNumeroProduto();
		this.dataHabilitacao = dto.getDataHabilitacao();
		this.plano = dto.getPlano();
		this.qtdDados = dto.getQtdDados();
		this.qtdSms = dto.getQtdSms();
		this.qtdMinutos = dto.getQtdMinutos();
	}

	public Produto atualizar(AtualizarProdutoDto dto, Cliente cliente) {
		this.cliente = cliente;
		this.numeroProduto = dto.getNumeroProduto();
		this.dataHabilitacao = dto.getDataHabilitacao();
		this.plano = dto.getPlano();
		this.qtdDados = dto.getQtdDados();
		this.qtdSms = dto.getQtdSms();
		this.qtdMinutos = dto.getQtdMinutos();
		return this;
	}
}
