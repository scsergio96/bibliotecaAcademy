package it.elearnpath.siav.libreria.converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Libro;

@Component
public class LibroToLibroDto {

    public LibroDTO convert(Libro libro) {
        final LibroDTO libroDTO = new LibroDTO();

        libroDTO.setId(libro.getId());
        libroDTO.setIsbn(libro.getIsbn());
        libroDTO.setTitolo(libro.getTitolo());
        libroDTO.setPagine(libro.getPagine());
        libroDTO.setPrimaPubblicazione(convertDate(libro.getPrimaEdizione()));
        libroDTO.setUltimaStampa(convertDate(libro.getUltimaRistampa()));
        libroDTO.setDescrizione(libro.getDescrizione());
        libroDTO.setCasaEditrice(libro.getCasaEditrice().getRagioneSociale());
        libroDTO.setGenere(libro.getGenere());
        libroDTO.setPosizione(libro.getPosizioneBiblioteca().getNumero());
        libroDTO.setRipiano(libro.getPosizioneBiblioteca().getRipiano());
        libroDTO.setLingua(libro.getLingua());
        libroDTO.setIdAutore(libro.getAutori().stream().map(autore -> autore.getId()).collect(Collectors.toList()));
        libroDTO.setNomeAutore(libro.getAutori().stream().map(autore -> autore.getNome()).collect(Collectors.toList()));
        libroDTO.setCognomeAutore(
                libro.getAutori().stream().map(autore -> autore.getCognome()).collect(Collectors.toList()));

        return libroDTO;
    }

    public String convertDate(Date date) {
        String pattern = "yyyy-MM-dd";

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String dateString = format.format(date);

        return dateString;
    }

}
