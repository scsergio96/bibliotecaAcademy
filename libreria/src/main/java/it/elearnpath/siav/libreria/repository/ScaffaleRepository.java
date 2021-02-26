package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Scaffale;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScaffaleRepository extends JpaRepository<Scaffale, Integer> {

    Optional<Scaffale> findByNumeroAndRipiano(Integer numero, Integer posizione); 
}
