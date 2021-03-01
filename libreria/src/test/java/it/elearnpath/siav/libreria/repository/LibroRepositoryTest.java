package it.elearnpath.siav.libreria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import it.elearnpath.siav.libreria.entity.Libro;


    
@SpringBootTest
public class LibroRepositoryTest {


    @Autowired
    private LibroRepository libroRepository;
        
    @Test
    public void testfindAllByOrderByTitoloAsc() {
        
        Page<Libro> libri = libroRepository.findAllByOrderByTitoloAsc(PageRequest.of(0, 10));
        assertEquals(10, libri.getSize());

    }

    @Test
    public void getAllGenres() {

        List<String> generi = libroRepository.getAllGenres();
        assertEquals(7, generi.size());

    }

    @Test
    public void getNumForGenres() {
        List<String> topGeneri = libroRepository.getNumForGenres();
        assertEquals(7, topGeneri.size());
    }

    @Test
    public void findByIsbnLike() {
        Optional<Libro> libro = libroRepository.findByIsbnLike("1158519514897");
        assertEquals(true, libro.isPresent());
    }

    @Test
    public void findAllByTitoloContainingIgnoreCase() {
        List<Libro> libri = libroRepository.findAllByTitoloContainingIgnoreCase("fasddas");
        assertEquals(2, libri.size());
    }

    @Test
    public void findAllByGenereIgnoreCase() {
        List<Libro> libri = libroRepository.findAllByGenereIgnoreCase("horror");
        assertEquals(3, libri.size());
    }

}
    