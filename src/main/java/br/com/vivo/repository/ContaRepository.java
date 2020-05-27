package br.com.vivo.repository;

import br.com.vivo.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByPeriodoFimEqualsAndValorIsNull(LocalDate dataAtual);

}
