package it.elearnpath.siav.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.elearnpath.siav.libreria.entity.Genere;

@Repository
public interface GenereRepository extends JpaRepository<Genere, Integer> {
    
}
