package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Libro;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {

    public Page<Libro>  findAllByOrderByTitoloAsc(Pageable pageable);

}
