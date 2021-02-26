package it.elearnpath.siav.libreria.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.elearnpath.siav.libreria.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.entity.Scaffale;

@Component
public class LibroDtoToLibro {

    @Autowired
    AutoreRepository autoreRepository;

    public Libro convert(LibroDTO libroDTO) {

        if (libroDTO == null) {
            return null;
        }

        Autore autore = new Autore();
        autore.setId(11);
        autore.setNome("alessandro");
        autore.setCognome("manzoni");
        autore.setNazionalita("italiana");
        autore.setBiografia("biografia di prova");
        List<Autore> autoriMock = new ArrayList<Autore>();
        autoriMock.add(autore);

        CasaEditrice casaEditrice = new CasaEditrice();
        casaEditrice.setId(1);
        casaEditrice.setRagioneSociale("Mondadori");
        casaEditrice.setIndirizzo("Via Roma");
        casaEditrice.setPIva("1234567890");

        Scaffale scaffale = new Scaffale();
        scaffale.setId(1);
        scaffale.setNumero(1);
        scaffale.setRipiano(1);

        final Libro libro = new Libro();

        libro.setId(0);
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setPagine(libroDTO.getPagine());
        libro.setPrimaEdizione(libroDTO.getPrimaPubblicazione());
        libro.setUltimaRistampa(libroDTO.getUltimaStampa());
        libro.setDescrizione(libroDTO.getDescrizione());
        libro.setAutori(autoriMock);
        libro.setCasaEditrice(casaEditrice);
        libro.setGenere(libroDTO.getGenere());
        libro.setPosizioneBiblioteca(scaffale);
        libro.setLingua(libroDTO.getLingua());

        return libro;

    }

    public Libro convertWithId(LibroDTO libroDTO) {

        if (libroDTO == null) {
            return null;
        }

        Autore autore = new Autore();
        autore.setId(11);
        autore.setNome("alessandro");
        autore.setCognome("manzoni");
        autore.setNazionalita("italiana");
        autore.setBiografia("biografia di prova");
        List<Autore> autoriMock = new ArrayList<Autore>();
        autoriMock.add(autore);

        CasaEditrice casaEditrice = new CasaEditrice();
        casaEditrice.setId(1);
        casaEditrice.setRagioneSociale("Mondadori");
        casaEditrice.setIndirizzo("Via Roma");
        casaEditrice.setPIva("1234567890");

        Scaffale scaffale = new Scaffale();
        scaffale.setId(1);
        scaffale.setNumero(1);
        scaffale.setRipiano(1);

        final Libro libro = new Libro();

        libro.setId(libroDTO.getId());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setPagine(libroDTO.getPagine());
        libro.setPrimaEdizione(libroDTO.getPrimaPubblicazione());
        libro.setUltimaRistampa(libroDTO.getUltimaStampa());
        libro.setDescrizione(libroDTO.getDescrizione());
        libro.setAutori(autoriMock);
        libro.setCasaEditrice(casaEditrice);
        libro.setGenere(libroDTO.getGenere());
        libro.setPosizioneBiblioteca(scaffale);
        libro.setLingua(libroDTO.getLingua());

        return libro;

    }

}
