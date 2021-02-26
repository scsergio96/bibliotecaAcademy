package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Autore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {

    List<Autore> findByOrderByCognomeAscNomeAsc(Pageable pageable);

    Optional<Autore> findById(Integer id);

    List<Autore> findAllById(Integer id);
}
