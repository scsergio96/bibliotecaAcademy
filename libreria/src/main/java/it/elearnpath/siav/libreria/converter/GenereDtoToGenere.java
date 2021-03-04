package it.elearnpath.siav.libreria.converter;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.GenereDTO;
import it.elearnpath.siav.libreria.entity.Genere;

@Component
public class GenereDtoToGenere {
    
    public Genere convert(GenereDTO genereDTO) {
        
        if (genereDTO == null) {
            return null;
        }

        final Genere genere = new Genere();

        genere.setGenere(genereDTO.getGenere());

        return genere;
    }

    public Genere convertWithId(GenereDTO genereDTO) {
        
        if (genereDTO == null) {
            return null;
        }

        final Genere genere = new Genere();

        genere.setId(genereDTO.getId());
        genere.setGenere(genereDTO.getGenere());

        return genere;
    }

}
