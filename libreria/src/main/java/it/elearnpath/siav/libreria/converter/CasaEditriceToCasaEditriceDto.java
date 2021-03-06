package it.elearnpath.siav.libreria.converter;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;

@Component
public class CasaEditriceToCasaEditriceDto {

    /**
     * Metodo che esegue la conversione da un oggetto DAO ad un DTO
     * @param casaEditrice oggetto DAO che vogliamo convertire
     * @return oggeto DTO convertito
     */
    public CasaEditriceDTO convert(CasaEditrice casaEditrice){
        
        final CasaEditriceDTO casaEditriceDTO = new CasaEditriceDTO();
        
        if(casaEditrice == null){
            return null;
        }

        casaEditriceDTO.setId(casaEditrice.getId());
        casaEditriceDTO.setRagioneSociale(casaEditrice.getRagioneSociale());
        casaEditriceDTO.setPIva(casaEditrice.getPIva());
        casaEditriceDTO.setIndirizzo(casaEditrice.getIndirizzo());
        
        return casaEditriceDTO;
    }

    
}
