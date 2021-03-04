package it.elearnpath.siav.libreria.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Libro;

@Component
public class LibroToLibroDto {

    @Autowired
    GenereToGenereDTO genereDTO;

    public LibroDTO convert(Libro libro) {

        if (libro == null) {
            return null;
        }

        final LibroDTO libroDTO = new LibroDTO();

        libroDTO.setId(libro.getId());
        libroDTO.setIsbn(libro.getIsbn());
        libroDTO.setTitolo(libro.getTitolo());
        libroDTO.setPagine(libro.getPagine());
        libroDTO.setPrimaPubblicazione(convertDateToString(libro.getPrimaEdizione()));
        libroDTO.setUltimaStampa(convertDateToString(libro.getUltimaRistampa()));
        libroDTO.setDescrizione(libro.getDescrizione());
        libroDTO.setCasaEditrice(libro.getCasaEditrice().getRagioneSociale());
        libroDTO.setGenere(genereDTO.convert(libro.getGenere()));
        libroDTO.setPosizione(libro.getPosizioneBiblioteca().getNumero());
        libroDTO.setRipiano(libro.getPosizioneBiblioteca().getRipiano());
        libroDTO.setLingua(libro.getLingua());
        libroDTO.setIdAutore(libro.getAutori().stream().map(autore -> autore.getId()).collect(Collectors.toList()));
        libroDTO.setNomeAutore(libro.getAutori().stream().map(autore -> autore.getNome()).collect(Collectors.toList()));
        libroDTO.setCognomeAutore(
                libro.getAutori().stream().map(autore -> autore.getCognome()).collect(Collectors.toList()));
        libroDTO.setIsAvailable(libro.getAvailable());
        libroDTO.setRistampa(libro.getRistampa());

        return libroDTO;
    }

    private static String convertDateToString(Date date) {
        if (date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date).toString();
        } else
            return null;
    }

}
