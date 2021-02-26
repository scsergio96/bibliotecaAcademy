package it.elearnpath.siav.libreria.service;

import java.util.List;

import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;

public interface CasaEditriceService {
    
    //List<CasaEditriceDTO> searchAll();

    List<CasaEditriceDTO> searchAll(Integer pageNo, Integer pageSize, String sortBy);

    CasaEditriceDTO searchById(Integer id);

    CasaEditriceDTO searchByRagSociale(String ragSociale);

    CasaEditriceDTO searchByPIva(String pIva);

    List<CasaEditriceDTO> searchByIdOrRagSocialeOrPiva(Integer id, String ragSoc, String pIva);

    void addNewCasaEdi(CasaEditriceDTO casaEditrice);

    void updateCasaEdi(CasaEditriceDTO casaEditrice);

    void deleteCasaEdi(Integer id);
}
