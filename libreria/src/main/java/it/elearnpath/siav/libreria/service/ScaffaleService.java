package it.elearnpath.siav.libreria.service;

import java.util.List;

import javax.validation.Valid;

import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;

public interface ScaffaleService {

   List<Scaffale> findAll();

   ScaffaleDTO findById(Integer id);

   void save(ScaffaleDTO scaffaleDTO);

   Scaffale saveUpdateScafale(ScaffaleDTO scaffale);

   void deleteById(Integer id);

}

