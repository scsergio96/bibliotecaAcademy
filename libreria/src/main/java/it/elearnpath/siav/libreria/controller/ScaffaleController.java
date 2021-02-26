package it.elearnpath.siav.libreria.controller;

import org.apache.logging.log4j.message.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

import it.elearnpath.siav.libreria.service.ScaffaleService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/scaffale")
public class ScaffaleController {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ScaffaleService scaffaleService;

    @GetMapping()
    public ResponseEntity<List<ScaffaleDTO>> findAll() {

        List<ScaffaleDTO> scaffaleDTO = scaffaleService.findAll()
                                                        .stream()
                                                        .map(scaffale -> modelMapper.map(scaffale, ScaffaleDTO.class))
                                                        .collect(Collectors.toList());

        

        return new ResponseEntity<List<ScaffaleDTO>>(scaffaleDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScaffaleDTO> findById(@PathVariable Integer id) {

        ScaffaleDTO scaffaleDTO = modelMapper.map(scaffaleService.findById(id), ScaffaleDTO.class);

        return new ResponseEntity<ScaffaleDTO>(scaffaleDTO, HttpStatus.OK);

    }

    @PostMapping("/inserisco")
    public ResponseEntity<?> inseriscoScaffale(@RequestBody  @Valid ScaffaleDTO scaffaleDTO, BindingResult bindingResult) throws DuplicateException,
            BindException {

        if(bindingResult.hasErrors()){
            throw new BindException("You have put an wrong value, check your date");
        }

       // scaffaleService.save(scaffale);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        Integer i = scaffaleDTO.getId();
        System.out.println(i);
        
    

        if(scaffaleService.findById(i) == null){
            scaffaleService.save(scaffaleDTO);
        }
        

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }
    
    @PutMapping("/aggiorno")
    public ResponseEntity<?> uploadScafale(@RequestBody ScaffaleDTO scaffaleDTO){

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        
        int i = scaffaleDTO.getId();

        if(scaffaleService.findById(i) != null){
            
           scaffaleService.save(scaffaleDTO);
        }

        

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
 
       @DeleteMapping("/elimina")
       public ResponseEntity<?> deleteScaffale(@RequestBody Scaffale scaffale){

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        
        scaffaleService.deleteById(scaffale.getId());

        

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eseguita Con Successo");

        return new ResponseEntity<>(headers, HttpStatus.OK);
       }
}
