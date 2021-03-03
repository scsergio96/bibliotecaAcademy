package it.elearnpath.siav.libreria.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpHeaders;
import java.net.BindException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.elearnpath.siav.libreria.dto.ScaffaleDTO;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.exception.DuplicateException;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.ScaffaleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/scaffale")
@Api(value = "scaffale", tags="Controller gestione Scaffale")
public class ScaffaleController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ScaffaleService scaffaleService;

    @Autowired
    private ResourceBundleMessageSource errMsg;


    @ApiOperation(
        value = "Elenco di tutte le scaffali",
        notes = "I dati sono restituiti in formato JSON",
        response = ScaffaleDTO.class,
        responseContainer = "List",
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tutto bene"),
        @ApiResponse(code = 400, message = "Errore generico")
    })
    @GetMapping()
    public ResponseEntity<List<ScaffaleDTO>> findAll(@RequestParam(defaultValue = "0") Integer pageNo, 
                                                     @RequestParam(defaultValue = "10") Integer pageSize,
                                                     @RequestParam(defaultValue = "id") String sortBy) {

//        List<ScaffaleDTO> scaffaleDTO = scaffaleService.findAll().stream()
//                .map(scaffale -> modelMapper.map(scaffale, ScaffaleDTO.class)).collect(Collectors.toList());

        List<ScaffaleDTO> scaffaleDTO = scaffaleService.findAll(pageNo, pageSize, sortBy); 
        
        if(scaffaleDTO.isEmpty()){
            return new ResponseEntity<List<ScaffaleDTO>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<ScaffaleDTO>>(scaffaleDTO, HttpStatus.OK);
    }
     

    @ApiOperation(
        value = "Ricerca scaffale per id",
        notes = "I dati sono ritornati in formato JSON",
        response = ScaffaleDTO.class,
        produces = "application/json"
    )
    @ApiResponses(value = {
        @ApiResponse(code=200, message ="Tutto bene"),
        @ApiResponse(code = 404, message = "Elemento non trovato")
    })
    @GetMapping("/search/{id}")
    public ResponseEntity<ScaffaleDTO> findById(@PathVariable Integer id) throws NotFoundException {

        ScaffaleDTO scaffaleDTO = modelMapper.map(scaffaleService.findById(id), ScaffaleDTO.class);

        if(scaffaleDTO == null){
            throw new NotFoundException("Non è presente alcun elemento con questo id " + id);
        }

        return new ResponseEntity<ScaffaleDTO>(scaffaleDTO, HttpStatus.OK);

    }
    @ApiOperation(
        value = "Inserimento di un scaffale se non presente nel DB",
        notes = "I dati sono ritornati in formato JSON",
        response = ScaffaleDTO.class,
        produces = "application/json"
    )
    @ApiResponses(value = {
        @ApiResponse(code=201, message ="Elemento inserito correttamente"),
        @ApiResponse(code = 406, message = "Elemento duplicato")
    })
    @PostMapping("/add")
    public ResponseEntity<ScaffaleDTO> inseriscoScaffale(@RequestBody @Valid ScaffaleDTO scaffaleDTO, BindingResult bindingResult)
            throws DuplicateException, BindException {

        if (bindingResult.hasErrors()) {

            String err = errMsg.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());

            throw new BindException(err);
        }

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

//        Integer i = scaffaleDTO.getId();
//        System.out.println(i);

        Scaffale scaffale = scaffaleService.findByNumeroAndRipiano(scaffaleDTO.getNumero(), scaffaleDTO.getRipiano());

        if (scaffale.getId() == -1) {
            scaffaleService.save(scaffaleDTO);
        }else{
            throw new DuplicateException("E' gia presente questo elemento");
        }

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<ScaffaleDTO>(headers, HttpStatus.CREATED);

    }



    @ApiOperation(
        value = "Aggiornamento di una casa editrice se presente in DB",
        notes = "I dati sono ritornati in formato JSON",
        response = ScaffaleDTO.class,
        produces = "application/json"
    )
    @ApiResponses(value = {
        @ApiResponse(code=201, message ="Elemento aggiornato correttamente"),
        @ApiResponse(code = 404, message = "Elemento non presente")
    })
    @PutMapping("/update")
    public ResponseEntity<?> updateScafale(@RequestBody ScaffaleDTO scaffaleDTO) throws NotFoundException {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        int i = scaffaleDTO.getId();

        if (scaffaleService.findById(i) != null) {
            scaffaleService.save(scaffaleDTO);
        }else{
            throw new NotFoundException("Scaffale non presente");
        }

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }



    @ApiOperation(
        value = "Cancellazione di un scaffale nel DB",
        notes = "I dati sono ritornati in formato JSON",
        response = ScaffaleDTO.class,
        produces = "application/json"
    )
    @ApiResponses(value = {
        @ApiResponse(code=200, message ="Elemento cancellato correttamente"),
        @ApiResponse(code = 404, message = "Elemento non presente")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ScaffaleDTO> deleteScaffale(@PathVariable Integer id) throws NotFoundException {

        ScaffaleDTO scaffaleDTO = scaffaleService.findById(id);

        if (scaffaleDTO == null) {
            throw new NotFoundException("Scaffale non presente");
        }
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        scaffaleService.deleteById(id);

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<ScaffaleDTO>(headers, HttpStatus.OK);
    }
}
