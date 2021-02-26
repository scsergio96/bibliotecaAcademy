package it.elearnpath.siav.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.elearnpath.siav.libreria.entity.Libro;

public interface LibroService {

    public List<Libro> getLibri();

    public Page<Libro> getLibri(Pageable pageable);

    public Optional<Libro> getLibro(Integer id);

    public void insLibro(Libro libro);

    public void delLibro(Integer id);

    public List<String> getGenres();

    public List<Libro> getLibroByIdOrIsbnOrTitolo(Integer id, String isbn, String titolo);

    public Optional<Libro> getLibroByIsbn(String isbn);

}
