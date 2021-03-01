package it.elearnpath.siav.libreria.converter;

import org.springframework.stereotype.Component;

import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;

@Component
public class CasaEditriceDtoToCasaEditrice {

    /**
     * Metodo che esegue la conversione da un oggetto DTO ad un DAO
     * @param casaEditriceDTO oggetto DTO che vogliamo convertire
     * @return oggeto DAO convertito
     */
    public CasaEditrice convert(CasaEditriceDTO casaEditriceDTO){

        if(casaEditriceDTO == null){
            return null;
        }

        final CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(0);
        casaEditrice.setIndirizzo(casaEditriceDTO.getIndirizzo());
        casaEditrice.setRagioneSociale(casaEditriceDTO.getRagioneSociale());
        casaEditrice.setPIva(casaEditriceDTO.getPIva());

        return casaEditrice;
        
    }


    public CasaEditrice convertWithId(CasaEditriceDTO casaEditriceDTO){

        if(casaEditriceDTO == null){
            return null;
        }

        final CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(casaEditriceDTO.getId());
        casaEditrice.setIndirizzo(casaEditriceDTO.getIndirizzo());
        casaEditrice.setRagioneSociale(casaEditriceDTO.getRagioneSociale());
        casaEditrice.setPIva(casaEditriceDTO.getPIva());

        return casaEditrice;
        
    }

}
