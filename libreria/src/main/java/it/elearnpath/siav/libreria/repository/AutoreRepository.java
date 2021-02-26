package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Autore;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutoreRepository extends JpaRepository<Autore, Integer> {

    List<Autore> findByOrderByCognomeAscNomeAsc(Pageable pageable);

    Optional<Autore> findById(Integer id);
}
