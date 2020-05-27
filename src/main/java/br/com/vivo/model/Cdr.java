package br.com.vivo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.vivo.dto.CriarCdrDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "cdr")
@EqualsAndHashCode(of = { "id" })
@NoArgsConstructor
public class Cdr implements Serializable {
	
	private static final long serialVersionUID = 7578310746541059042L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "origem", nullable = false)
	private String origem;

	@Column(name = "destino")
	private String destino;

	@Enumerated(EnumType.STRING)
	@Column(name = "formato", nullable = false)
	private Formato formato;

	@Column(name = "data_registro", nullable = false)
	private LocalDateTime dataRegistro;

	@Column(name = "duracao", nullable = false)
	private Integer duracao;

	public Cdr(CriarCdrDto dto) {
		this.origem = dto.getOrigem();
		this.destino = dto.getDestino();
		this.formato = dto.getFormato();
		this.dataRegistro = dto.getDataRegistro();
		this.duracao = dto.getDuracao();
	}
}
