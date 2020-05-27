package br.com.vivo.model;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.vivo.dto.AtualizarClienteDto;
import br.com.vivo.dto.ClienteDto;
import br.com.vivo.dto.CriarClienteDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.Converters;

@Getter
@Entity
@Table(name = "cliente")
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Cliente implements Serializable {

    private static final long serialVersionUID = 5325666395642081468L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Produto> produtos;

    public Cliente(CriarClienteDto dto) {
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.estado = dto.getEstado();
        this.produtos = dto.getProdutos().stream()
                .map(p -> new Produto(p, this))
                .collect(Collectors.toList());
    }

    public Cliente atualizar(AtualizarClienteDto dto) {
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.estado = dto.getEstado();
        dto.getProdutos().forEach(dtos -> {
            this.produtos.stream().map(produto -> produto.atualizar(dtos, this));
        });
        return this;
    }
}
