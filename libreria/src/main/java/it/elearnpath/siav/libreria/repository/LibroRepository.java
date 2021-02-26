package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Libro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LibroRepository extends JpaRepository<Libro, Integer> {

    public Page<Libro>  findAllByOrderByTitoloAsc(Pageable pageable);

    @Query(value = "SELECT genere FROM biblioteca.libro group by genere", nativeQuery = true)
    public List<String> getAllGenres();

    public Optional<Libro> findByIsbnLike(String isbn);

}
