package it.elearnpath.siav.libreria.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.AutoreRepository;
import it.elearnpath.siav.libreria.repository.CasaEditriceRepository;
import it.elearnpath.siav.libreria.service.ScaffaleService;

@Component
public class LibroDtoToLibro {

    @Autowired
    CasaEditriceRepository casaEditriceRepository;

    @Autowired
    ScaffaleService scaffaleService;

    @Autowired
    AutoreRepository autoreRepository;

    public Libro convert(LibroDTO libroDTO) {

        if (libroDTO == null) {
            return null;
        }

        List<Autore> autori = libroDTO.getIdAutore().stream().map(id -> autoreRepository.findById(id).get())
        .collect(Collectors.toList());

        Scaffale scaffale = scaffaleService.findByNumeroAndRipiano(libroDTO.getPosizione(),libroDTO.getRipiano());

        final Libro libro = new Libro();

        libro.setId(0);
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setPagine(libroDTO.getPagine());
        libro.setPrimaEdizione(libroDTO.getPrimaPubblicazione());
        libro.setUltimaRistampa(libroDTO.getUltimaStampa());
        libro.setDescrizione(libroDTO.getDescrizione());
        libro.setAutori(autori);
        libro.setCasaEditrice(casaEditriceRepository.findByRagioneSocialeLike(libroDTO.getCasaEditrice()));
        libro.setGenere(libroDTO.getGenere());
        libro.setPosizioneBiblioteca(scaffale);
        libro.setLingua(libroDTO.getLingua());

        return libro;


    }

    public Libro convertWithId(LibroDTO libroDTO) {

        if (libroDTO == null) {
            return null;
        }

        List<Autore> autori = libroDTO.getIdAutore().stream().map(id -> autoreRepository.findById(id).get())
        .collect(Collectors.toList());

        Scaffale scaffale = scaffaleService.findByNumeroAndRipiano(libroDTO.getPosizione(),libroDTO.getRipiano());

        final Libro libro = new Libro();

        libro.setId(libroDTO.getId());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setPagine(libroDTO.getPagine());
        libro.setPrimaEdizione(libroDTO.getPrimaPubblicazione());
        libro.setUltimaRistampa(libroDTO.getUltimaStampa());
        libro.setDescrizione(libroDTO.getDescrizione());
        libro.setAutori(autori);
        libro.setCasaEditrice(casaEditriceRepository.findByRagioneSocialeLike(libroDTO.getCasaEditrice()));
        libro.setGenere(libroDTO.getGenere());
        libro.setPosizioneBiblioteca(scaffale);
        libro.setLingua(libroDTO.getLingua());

        return libro;

    }

}
