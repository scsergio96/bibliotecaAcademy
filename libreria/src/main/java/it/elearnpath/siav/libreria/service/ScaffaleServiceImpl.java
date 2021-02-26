package it.elearnpath.siav.libreria.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.ScaffaleRepository;
import java.util.Optional;

@Service
public class ScaffaleServiceImpl implements ScaffaleService {

   ModelMapper modelMapper = new ModelMapper();

   @Autowired
   private ScaffaleRepository scaffaleRepository;

   @Override
   public List<Scaffale> findAll() {

      return scaffaleRepository.findAll();

   }

   @Override
   public ScaffaleDTO findById(Integer id) {

      Optional<Scaffale> scaffale = scaffaleRepository.findById(id);

      if (!scaffale.isPresent()) {
         return null;
      }

      ScaffaleDTO scaffaleDTO = modelMapper.map(scaffale.get(), ScaffaleDTO.class);
      return scaffaleDTO;

   }

   @Override
   public void save(ScaffaleDTO scaffaleDTO) {
      Scaffale scaffale = modelMapper.map(scaffaleDTO, Scaffale.class);
      scaffaleRepository.save(scaffale);

   }

   @Override
   public void deleteById(Integer id) {
      scaffaleRepository.deleteById(id);

   }

   @Override
   public void findByNumeroAndRipiano(Integer numero, Integer posizione) {
      Scaffale scaffale = scaffaleRepository.findByNumeroAndRipiano(numero, posizione);

      if (!scaffale.getNumero().equals(numero)) {

         Scaffale scaffaleCreo = new Scaffale();

         scaffaleCreo.setNumero(numero);
         scaffaleCreo.setRipiano(posizione);

         scaffaleRepository.save(scaffaleCreo);
      }

   }

}
