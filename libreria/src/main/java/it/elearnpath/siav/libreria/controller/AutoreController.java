package it.elearnpath.siav.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.AutoreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authors")
@RestController
public class AutoreController {

    private final AutoreService autoreService;

    public AutoreController(AutoreService autoreService) {
        this.autoreService = autoreService;
    }

    @ApiOperation(
        value = "Ricerca tutti gli autori presenti nel DB",
        notes = "I risultati son restituite in pagine da 10. La numerazione delle pagine inizia da 0. " +
                "I dati sono restituiti in formato JSON",
        response = AutoreDTO.class,
        produces = "application/json")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Tutto bene"),
        @ApiResponse(code = 400, message = "Errore generico")
    })
    @GetMapping("/search/all/{page}")
    public ResponseEntity<List<AutoreDTO>> getAllAuthorsPaging(@PathVariable("page") int page) {
        List<AutoreDTO> autori = autoreService.findAllPaging(page);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<List<AutoreDTO>>(autori, headers, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Ricerca autori per id",
            notes = "I dati sono restituiti in formato JSON",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @GetMapping("/search/{id}")
    public ResponseEntity<AutoreDTO> getAuthorById(@PathVariable("id") Integer id) throws NotFoundException {
        AutoreDTO autoreDTO = autoreService.getAuthorById(id);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<AutoreDTO>(autoreDTO, headers, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Inserisce un autore se non è presente nel DB",
            notes = "I dati dell'autore vengono prelevati dal body della request",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Autore inserito nel DB"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @PostMapping("/add")
    public ResponseEntity<AutoreDTO> insertAuthor(@RequestBody AutoreDTO autoreDTO) {
        autoreService.saveAuthor(autoreDTO);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.CREATED.toString());

        return new ResponseEntity<AutoreDTO>(headers, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Aggiornamento dell'autore tramite id",
            notes = "I dati vengono aggiornati solo se differiscono da quelli presenti nel DB o sono diversi da null",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @PutMapping("/update")
    public ResponseEntity<AutoreDTO> updateAuthorById(@RequestBody AutoreDTO autoreDTO) throws Exception {
        autoreService.updateAuthor(autoreDTO);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<AutoreDTO>(headers, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un autore dal db per id",
            notes = "I risultati son restituite in pagine da 10. La numerazione delle pagine inizia da 0. " +
                    "I dati sono restituiti in formato JSON",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AutoreDTO> deleteAuthorById(@PathVariable Integer id) throws NotFoundException {
        autoreService.deleteAuthorById(id);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<AutoreDTO>(headers, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un autore dal db per id",
            notes = "I risultati son restituite in pagine da 10. La numerazione delle pagine inizia da 0. " +
                    "I dati sono restituiti in formato JSON",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.OPTIONS)
    public ResponseEntity<AutoreDTO> deleteAuthorByIdOptions(@PathVariable Integer id) throws NotFoundException {
        autoreService.deleteAuthorById(id);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<AutoreDTO>(headers, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un autore dal db per id",
            notes = "I risultati son restituite in pagine da 10. La numerazione delle pagine inizia da 0. " +
                    "I dati sono restituiti in formato JSON",
            response = AutoreDTO.class,
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico")
    })
    @GetMapping("/delete/{id}")
    public ResponseEntity<AutoreDTO> deleteAuthorByIdGet(@PathVariable Integer id) throws NotFoundException {
        autoreService.deleteAuthorById(id);

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Access-Control-Allow-Origin", "*");

        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());

        return new ResponseEntity<AutoreDTO>(headers, HttpStatus.OK);
    }
}
