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

import it.elearnpath.siav.libreria.converter.CasaEditriceDtoToCasaEditrice;
import it.elearnpath.siav.libreria.converter.CasaEditriceToCasaEditriceDto;
import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.repository.CasaEditriceRepository;

@Service
public class CasaEditriceServiceImpl implements CasaEditriceService {

    private final CasaEditriceRepository casaEditriceRepository;
    private final CasaEditriceToCasaEditriceDto casaEditriceToCasaEditriceDto;
    private final CasaEditriceDtoToCasaEditrice casaEditriceDtoToCasaEditrice;
    private ModelMapper modelMapper = new ModelMapper();
  

    public CasaEditriceServiceImpl(CasaEditriceRepository casaEditriceRepository, CasaEditriceToCasaEditriceDto casaEditriceToCasaEditriceDto, CasaEditriceDtoToCasaEditrice casaEditriceDtoToCasaEditrice){
        this.casaEditriceRepository = casaEditriceRepository;
        this.casaEditriceToCasaEditriceDto = casaEditriceToCasaEditriceDto;
        this.casaEditriceDtoToCasaEditrice = casaEditriceDtoToCasaEditrice;
    }

    
    /**
     * Metodo che va ad effettuare la mappatura da una lista di entitò DAO impaginate ad una lista di entità DTO
     * @param <D> classe generica che definisce di che tipo sarano gli elementi dopo aver effetuato la conversione
     * @param <T> classe generica che definisce di che tipo saranno gli elementi che vogliamo convertire 
     * @param entities insieme di elementi da convertire
     * @param dtoClass classe in cui vogliamo convertire i nostri elementi entity
     * @return una lista di elelemnti convertiti ed impaginati
     */
    // private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    //     return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
    // } 

    /**
     * Metodo che va a restituire una lista, attraverso un sistema di paginazione, contente gli elementi ricercati nel db e convertiti in oggetti DAO
     * @param pageNo numero della pagina a cui ci stiamo riferendo
     * @param pagesize numero massimo di elementi che vogliamo visualizzare sulla pagina
     * @param sortBy campo tramite cui vogliamo fare l'ordinamento degli elementi ricercati
     * @return una lista contente gli oggetti DTO
     */
    // @Override
    // public List<CasaEditriceDTO> searchAll(Integer pageNo, Integer pageSize, String sortBy) {

    //     Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    //     Page<CasaEditrice> casaEditriceList = casaEditriceRepository.findAll(paging);

    //     Page<CasaEditriceDTO> casaEditriceDTOList = mapEntityPageIntoDtoPage(casaEditriceList, CasaEditriceDTO.class);

    //     if((casaEditriceDTOList).hasContent()){
    //         return casaEditriceDTOList.getContent();
    //     }else{
    //         return new ArrayList<CasaEditriceDTO>();
    //     }
    // }

    /**
     * Metodo che va a restituire una lista, attraverso un sistema di paginazione, contente gli elementi ricercati nel db e convertiti in oggetti DAO
     * @param pageNo numero della pagina a cui ci stiamo riferendo
     * @param pagesize numero massimo di elementi che vogliamo visualizzare sulla pagina
     * @param sortBy campo tramite cui vogliamo fare l'ordinamento degli elementi ricercati
     * @return una lista contente gli oggetti DTO
     */
    @Override
    public List<CasaEditriceDTO> searchAll(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<CasaEditrice> casaEditriceList = casaEditriceRepository.findAll(paging);

        List<CasaEditriceDTO> casaEditriceDTOList = casaEditriceList.stream()
                                                                    .map(casaEditrice -> casaEditriceToCasaEditriceDto.convert(casaEditrice))
                                                                    .collect(Collectors.toList());

        if(!casaEditriceDTOList.isEmpty()){
            return casaEditriceDTOList;
        }else{
            return new ArrayList<CasaEditriceDTO>();
        }
    }


    /**
     * Metodo che esegue la ricerca di una specifica casa editrice in base al suo id
     * @param id valore dell'id che vogliamo utilizzare per effettuare la ricerca nel db
     * @return se viene ritrovato un elemento viene restituito la conversione di questo altrimento viene restituito il valore null
     */
    @Override
    public CasaEditriceDTO searchById(Integer id) {
        
        Optional<CasaEditrice> casaEditriceOptional = casaEditriceRepository.findById(id);

        if(!casaEditriceOptional.isPresent()){
            return null;
        }
        
        CasaEditriceDTO casaEditriceDTO = casaEditriceToCasaEditriceDto.convert(casaEditriceOptional.get());

        return casaEditriceDTO;
    }


    /**
     * Metodo che esegue la ricerca di una specifica casa editrice in base alla sua ragione sociale
     * @param ragSociale noiminativo che vogliamo utilizzare per effettuare la ricerca nel db
     * @return se viene ritrovato un elemento viene restituito la conversione di questo altrimento viene restituito il valore null
     */
    @Override
    public CasaEditriceDTO searchByRagSociale(String ragSociale) {
        CasaEditrice casaEditrice = casaEditriceRepository.findByRagioneSocialeLike(ragSociale);

        if(casaEditrice == null){
            return null;
        }

        CasaEditriceDTO casaEditriceDTO = casaEditriceToCasaEditriceDto.convert(casaEditrice);

        return casaEditriceDTO;
    }

    /**
     * Metodo che esegue la ricerca di una specifica casa editrice in base alla sua partita iva
     * @param pIva partita iva che vogliamo utilizzare per effettuare la ricerca nel db
     * @return se viene ritrovato un elemento viene restituito la conversione di questo altrimento viene restituito il valore null
     */
    @Override
    public CasaEditriceDTO searchByPIva(String pIva) {

        CasaEditrice casaEditrice = casaEditriceRepository.findBypIvaLike(pIva);

        if(casaEditrice == null){
            return null;
        }

        CasaEditriceDTO casaEditriceDTO = casaEditriceToCasaEditriceDto.convert(casaEditrice);

        return casaEditriceDTO;
    }


    /**
     * Metodo che esegue la ricerca di una specifica casa editrice in base al id, alla ragione sociale e/o alla partita iva
     * @param id valore dell'id che vogliamo utilizzare per effettuare la ricerca nel db
     * @param ragSoc noiminativo che vogliamo utilizzare per effettuare la ricerca nel db
     * @param pIva partita iva che vogliamo utilizzare per effettuare la ricerca nel db
     * @return se viene ritrovato un elemento viene restituito la conversione di questo altrimento viene restituito il valore null
     */
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
                                                                     .map(casaEditrice -> casaEditriceToCasaEditriceDto.convert(casaEditrice))
                                                                     .collect(Collectors.toList());

        return casaEditriceDTOList;
    }
    

    /**
     * Metodo che effettua l'aggiunta di un nuovo elemento
     * @param casaEditrice oggetto DTO che verrà convertito in un DAO per poi essere aggiunto nel db
     */
    @Override
    public void addNewCasaEdi(CasaEditriceDTO casaEditrice) {
        
        //CasaEditrice casaEditrice1 = modelMapper.map(casaEditrice, CasaEditrice.class);

        CasaEditrice casaEditrice1 = casaEditriceDtoToCasaEditrice.convert(casaEditrice);

        casaEditriceRepository.save(casaEditrice1);

    }


    /**
     * Metodo che effettua l'aggiornamento di un elemento
     * @param casaEditrice oggetto DTO che verrà convertito in un DAO per poi essere inserito nel db al posto dell'elemento già presente
     */
    @Override
    public void updateCasaEdi(CasaEditriceDTO casaEditrice) { 
        
        CasaEditrice casaEditrice1 = casaEditriceDtoToCasaEditrice.convertWithId(casaEditrice);

        //CasaEditrice casaEditrice1 = modelMapper.map(casaEditrice, CasaEditrice.class);

        casaEditriceRepository.save(casaEditrice1);

    }


    /**
     * Metodo che effettua la cancellazione di un elemento
     * @param id id dell'elemnto che vogliamo cancellare
     */
    @Override
    public void deleteCasaEdi(Integer id) {
       
        Optional<CasaEditrice> casaEditriceOptional = casaEditriceRepository.findById(id);

        casaEditriceRepository.delete(casaEditriceOptional.get());
         
    }
  
}
