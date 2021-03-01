package it.elearnpath.siav.libreria.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

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
                                                                                Metodi di LETTURA dei dati
                                                                                */                                                
    @ApiOperation(
        value = "Elenco di tutte le case editrici", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        responseContainer = "List",
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 200, message = "Tutto bene"),
                          @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/search/all")
    public ResponseEntity<List<CasaEditriceDTO>> getAll(@RequestParam(defaultValue = "0") Integer pageNo, 
                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                        @RequestParam(defaultValue = "id") String sortBy){

        List<CasaEditriceDTO> casaEditriceDTOList = casaEditriceService.searchAll(pageNo, pageSize, sortBy);

        if(casaEditriceDTOList.isEmpty()){
            // non è presente alcuna casa editrice
            return new ResponseEntity<List<CasaEditriceDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<CasaEditriceDTO>>(casaEditriceDTOList, HttpStatus.OK);
    }



    @ApiOperation(
        value = "Ricerca casa editrice per id", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 200, message = "Tutto bene"),
                          @ApiResponse(code = 404, message = "Elemento non trovato") })
    @GetMapping(value = "/search/{id}")
    public ResponseEntity<CasaEditriceDTO> getById(@PathVariable("id") Integer id) throws NotFoundException {

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchById(id);

        if(casaEditriceDTO == null){
            // elemento non presente nel db
            throw new NotFoundException("non è presente una casa editrice con id pari a " + id);
        }

        return new ResponseEntity<CasaEditriceDTO>(casaEditriceDTO, HttpStatus.OK);
    }



    @ApiOperation(
        value = "Ricerca casa editrice per ragione sociale", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 200, message = "Tutto bene"),
                          @ApiResponse(code = 400, message = "Errore generico") })
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



    @ApiOperation(
        value = "Ricerca casa editrice per partita iva", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 200, message = "Tutto bene"),
                          @ApiResponse(code = 400, message = "Errore generico") })
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

    @ApiOperation(
            value = "Ricerca casa editrice per id, ragione sociale e/o partita iva (restituisce una lista vuota in caso di insucesso)",
            response = CasaEditriceDTO.class,
            responseContainer = "List",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autore trovato"),
            @ApiResponse(code = 404, message = "Nessun parametro inserito") })
    @GetMapping(value = "/search")
    public ResponseEntity<List<CasaEditriceDTO>> getByProva(@RequestParam(required = false) Integer id,
                                                            @RequestParam(required = false) String ragioneSociale,
                                                            @RequestParam(required = false) String pIva) throws NotFoundException {
                                                                
        if(id == null && ragioneSociale == null && pIva == null){
            throw new NotFoundException("Nessun parametro di ricerca inserito");
        }

        List<CasaEditriceDTO> casaEditriceDTOList = casaEditriceService.searchByIdOrRagSocialeOrPiva(id, ragioneSociale, pIva);

        return new ResponseEntity<List<CasaEditriceDTO>>(casaEditriceDTOList, HttpStatus.OK);
    }
    
    
                                                                                /*
                                                                                Metodi di MODIFICA dei dati
                                                                                */    
    
    @ApiOperation(
        value = "Inserimento di una casa editrice se non presente nel DB", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 201, message = "Elemento inserito correttamente"),
                          @ApiResponse(code = 406, message = "Elemento duplicato") })
    @PostMapping(value = "/add")
    public ResponseEntity<CasaEditriceDTO> addNewElement(@Valid @RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        if(bindingResult.hasErrors()){

            String errMsg = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale()); 


            throw new BindingException(errMsg);
        }

        CasaEditriceDTO casaEditriceDTO1 = casaEditriceService.searchByPIva(casaEditriceDTO.getPIva());
        
        if(casaEditriceDTO1 != null){
            throw new DuplicateException("Elemento gia presente");
        }


        casaEditriceService.addNewCasaEdi(casaEditriceDTO);

        return new ResponseEntity<CasaEditriceDTO>(HttpStatus.CREATED);
        
    }



    @ApiOperation(
        value = "Aggiornamento di una casa editrice se presente nel DB", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 201, message = "Elemento aggiornato correttamente"),
                          @ApiResponse(code = 404, message = "Elemento non presente") })
    @PutMapping(value = "/update")
    public ResponseEntity<CasaEditriceDTO> updateElement(@Valid @RequestBody CasaEditriceDTO casaEditriceDTO, BindingResult bindingResult) throws NotFoundException, BindingException {


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

        return new ResponseEntity<CasaEditriceDTO>(HttpStatus.CREATED);
    }



    @ApiOperation(
        value = "Cancellazione di una casa editrice se presente nel DB", 
        notes = "I dati sono restituiti in formato JSON", 
        response = CasaEditriceDTO.class, 
        produces = "application/json")
    @ApiResponses(value = 
                        { @ApiResponse(code = 200, message = "Elemento cancellatto correttamente"),
                          @ApiResponse(code = 404, message = "Elemento non presente") })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<CasaEditriceDTO> deleteElemento(@PathVariable("id") Integer id) throws NotFoundException {

        CasaEditriceDTO casaEditriceDTO = casaEditriceService.searchById(id);

        if(casaEditriceDTO == null){

            String errMsg = String.format("Casa editrice %s non presente !", id);

            throw new NotFoundException(errMsg);
        }

        casaEditriceService.deleteCasaEdi(id);

        return new ResponseEntity<CasaEditriceDTO>(HttpStatus.OK);
    
    }
}
