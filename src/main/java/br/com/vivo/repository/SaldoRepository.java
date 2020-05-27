package br.com.vivo.repository;

import br.com.vivo.model.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {

    @Query("from Saldo c where c.produto.id =:id")
    List<Saldo> findByProduto(Long id );
}
