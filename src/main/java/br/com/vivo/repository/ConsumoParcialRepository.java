package br.com.vivo.repository;

import br.com.vivo.model.ConsumoSaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoParcialRepository extends JpaRepository<ConsumoSaldo, Long> {

    @Query("from ConsumoSaldo c where c.produto.id =:id")
    List<ConsumoSaldo> findByProduto(Long id );
}
