package it.elearnpath.siav.registry.converter;

import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Reader;
import org.springframework.stereotype.Component;

@Component
public class ReaderDtoToReader {


    public Reader convert(ReaderDTO readerDTO){

        if(readerDTO == null){
            return null;
        }

        final Reader reader = new Reader();
        
        reader.setCardNumber(readerDTO.getCardNumber());
        reader.setName(readerDTO.getName());
        reader.setSurname(readerDTO.getSurname());
        reader.setAddress(readerDTO.getAddress());

        return reader;
    }
    

    public Reader convertWithId(ReaderDTO readerDTO){

        if(readerDTO == null){
            return null;
        }

        final Reader reader = new Reader();
        
        reader.setId(readerDTO.getId());
        reader.setCardNumber(readerDTO.getCardNumber());
        reader.setName(readerDTO.getName());
        reader.setSurname(readerDTO.getSurname());
        reader.setAddress(readerDTO.getAddress());

        return reader;
    }
        
}
