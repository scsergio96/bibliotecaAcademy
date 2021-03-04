package it.elearnpath.siav.libreria.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.elearnpath.siav.libreria.entity.Genere;

@Repository
public interface GenereRepository extends JpaRepository<Genere, Integer> {
    
    public Optional<Genere> findByGenereIgnoreCase(String genere);     
}
