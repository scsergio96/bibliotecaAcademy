package it.elearnpath.siav.libreria.converter;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.GenereDTO;
import it.elearnpath.siav.libreria.entity.Genere;

@Component
public class GenereToGenereDTO {
    

    public GenereDTO convert(Genere genere) {
        
        if (genere== null) {
            return null;
        }

        final GenereDTO genereDTO = new GenereDTO();

        genereDTO.setId(genere.getId());
        genereDTO.setGenere(genere.getGenere());

        return genereDTO;
    }
}
