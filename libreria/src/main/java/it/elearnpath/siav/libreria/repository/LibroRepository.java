package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.Libro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LibroRepository extends JpaRepository<Libro, Integer> {

    public Page<Libro>  findAllByOrderByTitoloAsc(Pageable pageable);

    @Query(value = "SELECT genere FROM biblioteca.libro group by genere", nativeQuery = true)
    public List<String> getAllGenres();

    @Query(value = "SELECT COUNT(*), genere FROM libro GROUP BY genere;", nativeQuery = true)
    public List<String> getNumForGenres();

    // @Query(value = "SELECT COUNT(*), genere FROM libro GROUP BY genere;", nativeQuery = true)
    // public List<String> getNumForGenres();

    public Optional<Libro> findByIsbnLike(String isbn);

    public List<Libro> findAllByTitoloContainingIgnoreCase(String titolo);

    @Query(value = "SELECT * FROM biblioteca.libro where genere=:id", nativeQuery = true)
    public List<Libro> findAllByGenere(@Param ("id") Integer id);

    @Query(value = "SELECT libro.id,isbn,titolo,genere,pagine,ristampa,descrizione,lingua,primaEdizione,ultimaRistampa,casaEditrice,posizioneBiblioteca,isAvailable from autore_libro inner join libro on autore_libro.idLibro = libro.id inner join autore on autore_libro.idAutore = autore.id where autore.id=:id" , nativeQuery = true)
    public List<Libro> findAllLibroByAutore(@Param("id") Integer id);

}
