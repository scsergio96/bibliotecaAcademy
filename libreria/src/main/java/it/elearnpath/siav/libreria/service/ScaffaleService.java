package it.elearnpath.siav.libreria.service;

import java.util.List;

import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;

public interface ScaffaleService {

   List<ScaffaleDTO> findAll(Integer pageNo, Integer pageSize, String sortBy);

   ScaffaleDTO findById(Integer id);

   void save(ScaffaleDTO scaffaleDTO);

   void deleteById(Integer id);

   Scaffale findByNumeroAndRipiano(Integer numero, Integer posizione);

}
