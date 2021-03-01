package it.elearnpath.siav.registry.converter;

import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Reader;

public class ReaderConverter {

    public static ReaderDTO convert(Reader reader){

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

    public static Reader convert(ReaderDTO readerDTO){

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


    public static Reader convertWithId(ReaderDTO readerDTO){

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
