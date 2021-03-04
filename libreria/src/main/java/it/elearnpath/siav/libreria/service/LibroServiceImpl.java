package it.elearnpath.siav.libreria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.dto.CountGenresDTO;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Optional<Libro> getLibro(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro insLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void delLibro(Integer id) {
        libroRepository.deleteById(id);
    }

    @Override
    public Page<Libro> getLibri(Pageable pageable) {
        Page<Libro> result = libroRepository.findAllByOrderByTitoloAsc(pageable);
        return result;
    }

    @Override
    public List<Libro> getLibri() {
        return libroRepository.findAll();
    }

    @Override
    public List<String> getGenres() {
        return libroRepository.getAllGenres();
    }

    @Override
    public List<Libro> getLibroByIdOrIsbnOrTitolo(Integer id, String isbn, String titolo) {
        Libro libro = new Libro();
        libro.setId(id);
        libro.setIsbn(isbn);
        libro.setTitolo(titolo);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreCase("titolo");

        List<Libro> libri = libroRepository.findAll(Example.of(libro, exampleMatcher));
        return libri;
    }

    @Override
    public Optional<Libro> getLibroByIsbn(String isbn) {
        return libroRepository.findByIsbnLike(isbn);
    }

    @Override
    public List<Libro> getLibriByTitolo(String titolo) {
        return libroRepository.findAllByTitoloContainingIgnoreCase(titolo);
    }


    // @Override
    // public List<CountGenresDTO> getNumGenre(){
    //     List<String> numGenre = libroRepository.getNumForGenres();
    //     List<CountGenresDTO> countGenresDTOList = numGenre.stream()
    //                                                       .map(num -> new CountGenresDTO((Integer.parseInt(num.substring(0,1))), num.substring(2,3), num.substring(4)))
    //                                                       .collect(Collectors.toList());


    //     return countGenresDTOList;
    // }

        @Override
    public List<CountGenresDTO> getNumGenre() {
        List<CountGenresDTO> numGenre = libroRepository.getNumForGenres();
        return numGenre;
    }

    @Override
    public List<Libro> getLibriByGenere(Integer id) {
        return libroRepository.findAllByGenere(id);
    }

    @Override
    public List<Libro> getLibriByAutore(Integer id) {
        return libroRepository.findAllLibroByAutore(id);
    }

    @Override
    public List<Libro> getLibriByTitoloLike(String titolo) {
        return libroRepository.findAllByTitoloLike(titolo);
    }
}
