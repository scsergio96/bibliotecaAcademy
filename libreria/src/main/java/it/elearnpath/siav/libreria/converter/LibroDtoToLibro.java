package it.elearnpath.siav.libreria.converter;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Libro;

@Component
public class LibroDtoToLibro {

    public Libro convert(LibroDTO libroDTO) {

        if (libroDTO == null) {
            return null;
        }

        final Libro libro = new Libro();

        libro.setId(libroDTO.getId());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setTitolo(libroDTO.getTitolo());
        libro.setPagine(libroDTO.getPagine());
        libro.setPrimaEdizione(libroDTO.getPrimaPubblicazione());
        libro.setUltimaRistampa(libroDTO.getUltimaStampa());
        libro.setDescrizione(libroDTO.getDescrizione());

        return libro;

    }
    
}
