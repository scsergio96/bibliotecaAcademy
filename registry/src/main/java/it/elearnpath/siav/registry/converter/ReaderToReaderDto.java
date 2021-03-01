package it.elearnpath.siav.registry.converter;

import org.springframework.context.annotation.Configuration;

import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Reader;

@Configuration
public class ReaderToReaderDto {

    public ReaderDTO convert(Reader reader){

        if(reader == null){
            return null;
        }

        final ReaderDTO readerDTO = new ReaderDTO();

        readerDTO.setId(reader.getId());
        readerDTO.setCardNumber(reader.getCardNumber());
        readerDTO.setName(reader.getName());
        readerDTO.setSurname(reader.getSurname());
        readerDTO.setAddress(reader.getAddress());

        return readerDTO;
    }
    
}
