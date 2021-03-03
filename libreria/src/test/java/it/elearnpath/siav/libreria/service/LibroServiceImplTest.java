package it.elearnpath.siav.libreria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;

import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.LibroRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LibroServiceImplTest {

    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroServiceImpl;

  

    @Test
    public void testGetLibro() {
        final Integer id = 1;
        final Libro libro = new Libro();
        final CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(1);
        casaEditrice.setIndirizzo("Via Roma");
        casaEditrice.setPIva("12345678901");
        casaEditrice.setRagioneSociale("Editore1");

        final Scaffale scaffale = new Scaffale();
        scaffale.setId(1);
        scaffale.setNumero(1);
        scaffale.setRipiano(1);

        final Autore autore = new Autore();
        autore.setId(1);
        autore.setNome("nome");
        autore.setCognome("cognome");
//        autore.setDataNascita("2020-10-10");
//        autore.setDataMorte("2010-10-09");
        autore.setNazionalita("italiana");
        autore.setBiografia("biografia di prova");

        final List<Autore> autori = new ArrayList<Autore>();
        autori.add(autore);

        libro.setId(id);
        libro.setIsbn("12345678911");
        libro.setTitolo("prova");
        libro.setGenere("prova");
        libro.setPagine(100);
        libro.setRistampa(1);
        libro.setDescrizione("Descrizione");
        libro.setLingua("Italiano");
        libro.setPrimaEdizione("2000-10-10");
        libro.setUltimaRistampa("2010-10-10");
        libro.setAvailable(true);
        libro.setCasaEditrice(casaEditrice);
        libro.setPosizioneBiblioteca(scaffale);
        libro.setAutori(autori);

        doReturn(Optional.of(libro)).when(libroRepository).findById(id);

        Optional<Libro> libroFromService = libroServiceImpl.getLibro(id);

        Assertions.assertTrue(libroFromService.isPresent());
        Assertions.assertSame(libro, libroFromService.get());

        // when(libroRepository.findById(id)).thenReturn(Optional.of(libro));

        // final Optional<Libro> result = libroServiceImpl.getLibro(id);

        // assertEquals(result.get(), libro);

    }

    @Test
    public void testGetLibroErr() {
        final Integer id = 5000;

        doReturn(Optional.empty()).when(libroRepository).findById(id);

        Optional<Libro> libroFromService = libroServiceImpl.getLibro(id);

        Assertions.assertFalse(libroFromService.isPresent());
    }


    @Test
    public void testAddLibro() {

        final Libro libro = new Libro();
        final CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(1);
        casaEditrice.setIndirizzo("Via Roma");
        casaEditrice.setPIva("12345678901");
        casaEditrice.setRagioneSociale("Editore1");

        final Scaffale scaffale = new Scaffale();
        scaffale.setId(1);
        scaffale.setNumero(1);
        scaffale.setRipiano(1);

        final Autore autore = new Autore();
        autore.setId(1);
        autore.setNome("nome");
        autore.setCognome("cognome");
//        autore.setDataNascita("2020-10-10");
//        autore.setDataMorte("2010-10-09");
        autore.setNazionalita("italiana");
        autore.setBiografia("biografia di prova");

        final List<Autore> autori = new ArrayList<Autore>();
        autori.add(autore);

        libro.setIsbn("12345678911");
        libro.setTitolo("prova unit service");
        libro.setGenere("prova");
        libro.setPagine(100);
        libro.setRistampa(1);
        libro.setDescrizione("Descrizione");
        libro.setLingua("Italiano");
        libro.setPrimaEdizione("2000-10-10");
        libro.setUltimaRistampa("2010-10-10");
        libro.setAvailable(true);
        libro.setCasaEditrice(casaEditrice);
        libro.setPosizioneBiblioteca(scaffale);
        libro.setAutori(autori);


        doReturn(libro).when(libroRepository).save(any());

        Libro libroSave = libroServiceImpl.insLibro(libro);
        Assertions.assertNotNull(libroSave);
    }
    
}
