package br.com.vivo.repository;

import br.com.vivo.model.Cdr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdrRepository extends JpaRepository<Cdr, Long> {

    List<Cdr> findByOrigem(String Origem);
}
