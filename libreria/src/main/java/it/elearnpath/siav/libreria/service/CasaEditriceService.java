package it.elearnpath.siav.libreria.service;

import java.util.List;

import org.springframework.data.domain.Page;


import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;

public interface CasaEditriceService {
    
    //List<CasaEditriceDTO> searchAll();

    List<CasaEditriceDTO> searchAll(Integer pageNo, Integer pageSize, String sortBy);

    CasaEditriceDTO searchById(Integer id);

    void addNewCasaEdi(CasaEditriceDTO casaEditrice);

    void updateCasaEdi(CasaEditriceDTO casaEditrice);

    void deleteCasaEdi(Integer id);
}
