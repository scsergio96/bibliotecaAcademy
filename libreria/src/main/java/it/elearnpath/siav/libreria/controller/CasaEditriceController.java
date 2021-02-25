package it.elearnpath.siav.libreria.controller;

import java.util.List;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
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


    @GetMapping(value = "/search/id/{id}")
    public ResponseEntity<CasaEditriceDTO> getById(@PathVariable("id") Integer id) throws NotFoundException {

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchById(id);

        if(casaEditriceDTO == null){
            // elemento non presente nel db

            throw new NotFoundException("non è presente una casa editrice con id pari a " + id);
        }

        return new ResponseEntity<CasaEditriceDTO>(casaEditriceDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/search/ragioneSoc/{ragSoc}")
    public ResponseEntity<CasaEditriceDTO> getByRagSocial(@PathVariable("ragSoc") String ragSoc)
            throws Exception {

        if(ragSoc.length() == 0){
            throw new Exception("fornire una ragione sociale");
        }

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchByRagSociale(ragSoc + "%");

        if(casaEditriceDTO == null){
            throw new NotFoundException("Non è presente alcuna casa editrice che abbia questa ragione sociale " + ragSoc);
        }

        return new ResponseEntity<CasaEditriceDTO>(casaEditriceDTO, HttpStatus.OK);

    }


    @GetMapping(value = "/search/piva/{pIva}")
    public ResponseEntity<CasaEditriceDTO> getByPIva(@PathVariable("pIva") String pIva) throws Exception {

        if(pIva.length() != 11){
            throw new Exception("la lunghezza della partita iva deve essere pari a 11");
        }

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchByPIva(pIva);

        if(casaEditriceDTO == null){
            throw new NotFoundException("Non è presente alcuna casa editrice che abbia questa partita iva" + pIva);
        }

        return new ResponseEntity<CasaEditriceDTO>(casaEditriceDTO, HttpStatus.OK);

    }
    
    
                                                                                /*
                                                                                Metodi di modifica dei dati
                                                                                */    
    
 
    @PostMapping(value = "/add")
    public ResponseEntity<?> addNewElement(@Valid @RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        if(bindingResult.hasErrors()){

            String errMsg = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()); 


            throw new BindingException(errMsg);
        }


        if(casaEditriceDTO != casaEditriceService.searchByPIva(casaEditriceDTO.getPIva())){
            throw new DuplicateException("casa editrice già presente");
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


    @PutMapping(value = "/update")
    public ResponseEntity<?> updateElement(@Valid @RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult) throws NotFoundException, BindingException {


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
