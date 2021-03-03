package it.elearnpath.siav.libreria.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.ScaffaleRepository;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ScaffaleServiceImpl implements ScaffaleService {

   ModelMapper modelMapper = new ModelMapper();

   @Autowired
   private ScaffaleRepository scaffaleRepository;

   private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
         return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
   } 

   @Override
   public List<ScaffaleDTO> findAll(Integer pageNo, Integer pageSize, String sortBy) {

      Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

      Page<Scaffale> scaffaleList = scaffaleRepository.findAll(paging);

      Page<ScaffaleDTO> scaffaleDTOList = mapEntityPageIntoDtoPage(scaffaleList, ScaffaleDTO.class);

      if(scaffaleDTOList.hasContent()){
         return scaffaleDTOList.getContent();
      }else{
         return new ArrayList<ScaffaleDTO>();
      }

      //return scaffaleRepository.findAll();

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
   public Scaffale findByNumeroAndRipiano(Integer numero, Integer posizione) {
      
      Scaffale scaffale = scaffaleRepository.findByNumeroAndRipiano(numero, posizione);

      if (scaffale == null) {
         Scaffale scaffaleCreo = new Scaffale();
         scaffaleCreo.setId(-1);
         return scaffaleCreo;
      }

      return scaffale;

   }

}
