package it.elearnpath.siav.libreria.service;

import java.util.ArrayList;

/**
 * @author Madalin Andrei Nadejde
 * @version 1.0
 */

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.repository.CasaEditriceRepository;

@Service
public class CasaEditriceServiceImpl implements CasaEditriceService {

    private final CasaEditriceRepository casaEditriceRepository;
    private ModelMapper modelMapper = new ModelMapper();
  

    public CasaEditriceServiceImpl(CasaEditriceRepository casaEditriceRepository){
        this.casaEditriceRepository = casaEditriceRepository;
    }

    
    //metodo che restituisce una lista contenete le informazioni di tutte le case editrici presenti
    
    //  @Override
    //  public List<CasaEditriceDTO> searchAll() {

    //      List<CasaEditrice> casaEditriceList = casaEditriceRepository.findAll();

    //      List<CasaEditriceDTO> casaEditriceDTOList =  casaEditriceList.stream()
    //                                                                   .map(casaEditrice -> modelMapper.map(casaEditrice, CasaEditriceDTO.class))
    //                                                                   .collect(Collectors.toList());
    //      return casaEditriceDTOList;
    //  }


    private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    } 

    @Override
    public List<CasaEditriceDTO> searchAll(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<CasaEditrice> casaEditriceList = casaEditriceRepository.findAll(paging);

        Page<CasaEditriceDTO> casaEditriceDTOList = mapEntityPageIntoDtoPage(casaEditriceList, CasaEditriceDTO.class);

        if((casaEditriceDTOList).hasContent()){
            return casaEditriceDTOList.getContent();
        }else{
            return new ArrayList<CasaEditriceDTO>();
        }
    }

    @Override
    public CasaEditriceDTO searchById(Integer id) {
        
        Optional<CasaEditrice> casaEditriceOptional = casaEditriceRepository.findById(id);

        if(!casaEditriceOptional.isPresent()){
            return null;
        }
        
        CasaEditriceDTO casaEditriceDTO = modelMapper.map(casaEditriceOptional.get(), CasaEditriceDTO.class);

        return casaEditriceDTO;
    }


    @Override
    public CasaEditriceDTO searchByRagSociale(String ragSociale) {
        CasaEditrice casaEditrice = casaEditriceRepository.findByRagioneSocialeLike(ragSociale);

        CasaEditriceDTO casaEditriceDTO = modelMapper.map(casaEditrice, CasaEditriceDTO.class);

        return casaEditriceDTO;
    }

    @Override
    public CasaEditriceDTO searchByPIva(String pIva) {

        CasaEditrice casaEditrice = casaEditriceRepository.findBypIvaLike(pIva);

        if(casaEditrice == null){
            return null;
        }

        CasaEditriceDTO casaEditriceDTO = modelMapper.map(casaEditrice, CasaEditriceDTO.class);

        return casaEditriceDTO;
    }


    @Override
    public List<CasaEditriceDTO> searchByIdOrRagSocialeOrPiva(Integer id, String ragSoc, String pIva) {

        CasaEditrice casaEditriceExampler = new CasaEditrice();
        casaEditriceExampler.setId(id);
        casaEditriceExampler.setRagioneSociale(ragSoc);
        casaEditriceExampler.setPIva(pIva);

        ExampleMatcher matcher = ExampleMatcher.matching()
                                                .withIgnoreCase("ragioneSociale");


        List<CasaEditrice> casaEditriceList = casaEditriceRepository.findAll(Example.of(casaEditriceExampler, matcher));
        
        
        List<CasaEditriceDTO> casaEditriceDTOList =  casaEditriceList.stream()
                                                                .map(casaEditrice -> modelMapper.map(casaEditrice, CasaEditriceDTO.class))
                                                                .collect(Collectors.toList());

        return casaEditriceDTOList;
    }
    

    @Override
    public void addNewCasaEdi(CasaEditriceDTO casaEditrice) {
        
        CasaEditrice casaEditrice1 = modelMapper.map(casaEditrice, CasaEditrice.class);

        casaEditriceRepository.save(casaEditrice1);

    }

    @Override
    public void updateCasaEdi(CasaEditriceDTO casaEditrice) { 
        
        CasaEditrice casaEditrice1 = modelMapper.map(casaEditrice, CasaEditrice.class); 

        casaEditriceRepository.save(casaEditrice1);

    }

    @Override
    public void deleteCasaEdi(Integer id) {
       
        Optional<CasaEditrice> casaEditriceOptional = casaEditriceRepository.findById(id);

        casaEditriceRepository.delete(casaEditriceOptional.get());
         
    }
  
}
