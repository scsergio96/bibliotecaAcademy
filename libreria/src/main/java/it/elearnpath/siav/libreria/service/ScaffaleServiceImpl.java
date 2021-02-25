package it.elearnpath.siav.libreria.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.ScaffaleRepository;

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
   public ScaffaleDTO findById(Integer id){

      ScaffaleDTO scaffaleDTO = modelMapper.map(scaffaleRepository.findById(id).get(), ScaffaleDTO.class);

     return scaffaleDTO;
   }
   
   @Override
   public void save(ScaffaleDTO scaffaleDTO){
      Scaffale scaffale = modelMapper.map(scaffaleDTO, Scaffale.class);
      scaffaleRepository.save(scaffale);

   }

   @Override
   public Scaffale saveUpdateScafale(ScaffaleDTO scaffaleDTO){
       Scaffale scaffale1 = modelMapper.map(scaffaleDTO, Scaffale.class);
       return scaffale1;
   }

   @Override
   public void deleteById(Integer id){
      scaffaleRepository.deleteById(id);

   }

}
