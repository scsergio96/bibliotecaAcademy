package it.elearnpath.siav.libreria.testMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;

public class ExamMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkModelMapper(){
        CasaEditriceDTO creation = new CasaEditriceDTO();
        creation.setId(1);
        creation.setRagioneSociale("ragioneSociale");
        creation.setIndirizzo("indirizzo");
        creation.setPIva("pIva");

        CasaEditrice exam = modelMapper.map(creation, CasaEditrice.class);

        assertEquals(creation.getId(), exam.getId());
        assertEquals(creation.getRagioneSociale(), exam.getRagioneSociale());
    }

    @Test
    public void checkModelMapperInv(){
        CasaEditrice creation = new CasaEditrice();
        creation.setId(1);
        creation.setRagioneSociale("ragioneSociale");
        creation.setIndirizzo("indirizzo");
        creation.setPIva("pIva");

        CasaEditriceDTO exam = modelMapper.map(creation, CasaEditriceDTO.class);

        assertEquals(creation.getId(), exam.getId());
        assertEquals(creation.getRagioneSociale(), exam.getRagioneSociale());
    }



}
