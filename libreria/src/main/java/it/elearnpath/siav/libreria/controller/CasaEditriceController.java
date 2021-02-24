package it.elearnpath.siav.libreria.controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.exception.BindingException;
import it.elearnpath.siav.libreria.exception.DuplicateException;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.CasaEditriceService;

@RestController
@RequestMapping(value = "/editors")
@Api(value = "casa_editrice", tags = "Controller gestione casa editrice")
public class CasaEditriceController {


    private final CasaEditriceService casaEditriceService;
    private final ResourceBundleMessageSource errMessage;

    public CasaEditriceController(CasaEditriceService casaEditriceService, ResourceBundleMessageSource errMessage) {
        this.casaEditriceService = casaEditriceService;
        this.errMessage = errMessage;
    }

                                                                                /*
                                                                                Metodi di lettura dei dati
                                                                                */                                                

    @ApiOperation(
        value = "Ricerca di tutte le case editrici presenti",
        notes = "Restituisce i dati usando il formato dati JSON",
        response = CasaEditriceDTO.class,
        produces = "application/json"
    )
    @GetMapping(value = "/search/all")
    public ResponseEntity<List<CasaEditriceDTO>> getAll(@RequestParam(defaultValue = "0") Integer pageNo, 
                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy){

        List<CasaEditriceDTO> casaEditriceDTOList = casaEditriceService.searchAll(pageNo, pageSize, sortBy);

        if(casaEditriceDTOList.isEmpty()){
            // non è presente alcuna casa editrice
            return new ResponseEntity<List<CasaEditriceDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<CasaEditriceDTO>>(casaEditriceDTOList, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(
        value = "Ricerca di una specifica casa editrice",
        notes = "Restituisce i dati usando il formato dati JSON",
        response = CasaEditriceDTO.class,
        produces = "application/json"
    )
    @GetMapping(value = "/search/id/{id}")
    public ResponseEntity<CasaEditriceDTO> getById(@PathVariable("id") Integer id) throws NotFoundException {

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchById(id);

        if(casaEditriceDTO == null){
            // elemento non presente nel db

            throw new NotFoundException("non è presente una casa editrice con id pari a " + id);
        }

        return new ResponseEntity<CasaEditriceDTO>(casaEditriceDTO, HttpStatus.OK);
    }
    
    
                                                                                /*
                                                                                Metodi di modifica dei dati
                                                                                */    
    
    @ApiOperation(
        value = "Aggiunta di una nuova casa editrice",
        response = CasaEditriceDTO.class
    )
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewElement(@RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        if(bindingResult.hasErrors()){

            String errMsg = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()); 


            throw new BindingException(errMsg);
        }

        CasaEditriceDTO casaEditriceDTO1 = casaEditriceService.searchById(casaEditriceDTO.getId());


        if(casaEditriceDTO1 != null){
            // elemento gia presente 
            throw new DuplicateException("Casa Editrice gia presente con id pari a " + casaEditriceDTO.getId());
        }

        casaEditriceService.addNewCasaEdi(casaEditriceDTO);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = mapper.createObjectNode();

        casaEditriceService.addNewCasaEdi(casaEditriceDTO);

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Inserimento Articolo " + casaEditriceDTO.getId() + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
    }

    @ApiOperation(
        value = "Update di una casa editrice",
        response = CasaEditriceDTO.class
    )
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateElement(@RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult) throws NotFoundException, BindingException {


        if(bindingResult.hasErrors()){

            String errMsg = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()); 

            throw new BindingException(errMsg);
        }

        CasaEditriceDTO casaEditriceDTO1 = casaEditriceService.searchById(casaEditriceDTO.getId());

        if(casaEditriceDTO1 == null){
            // elemento non presente
            throw new NotFoundException("Non è presente alcuna casa editrice con id pari a " + casaEditriceDTO.getId());
        }

        casaEditriceService.addNewCasaEdi(casaEditriceDTO);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = mapper.createObjectNode();


        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Inserimento Articolo " + casaEditriceDTO.getId() + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, headers, HttpStatus.CREATED);
    }


    @ApiOperation(
        value = "Cancella di una casa editrice",
        response = CasaEditriceDTO.class
    )
    @DeleteMapping(value = "/delete/id/{id}")
    public ResponseEntity<?> deleteElemento(@PathVariable("id") Integer id){

        casaEditriceService.deleteCasaEdi(id);


        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Articolo " + id + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
    
    }
}
