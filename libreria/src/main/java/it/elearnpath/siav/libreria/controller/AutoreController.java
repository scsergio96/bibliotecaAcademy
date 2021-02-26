package it.elearnpath.siav.libreria.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.exception.BindingException;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.AutoreServiceImpl;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/authors")
@RestController
public class AutoreController {

    private final AutoreServiceImpl autoreService;
    private final ResourceBundleMessageSource errMessage;

    public AutoreController(AutoreServiceImpl autoreService, ResourceBundleMessageSource errMessage) {
        this.autoreService = autoreService;
        this.errMessage = errMessage;
    }

    @ApiOperation(
            value = "Ricerca tutti gli autori presenti nel DB.",
            notes = "I risultati son restituiti in pagine da 10. La numerazione delle pagine inizia da 0. Gli autori" +
                    "vengono ordinati alfabeticamente per cognome e nome",
            response = AutoreDTO.class,
            responseContainer = "List",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ricerca avvenuta con successo"),
            @ApiResponse(code = 400, message = "Errore generico (comprende errori di binding)")
    })
    @GetMapping("/search/all/{page}")
    public ResponseEntity<List<AutoreDTO>> getAllAuthorsPaging(@PathVariable("page") int page) {

        List<AutoreDTO> autori = autoreService.findAllPaging(page);

        return new ResponseEntity<List<AutoreDTO>>(autori, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Ricerca autori per id, nome e/o cognome. Ritorna una lista vuota se la ricerca non ha successo",
            notes = "La ricerca viene effettuata tramite query string (campi opzionali). L'ordine di inserimento" +
                    "è ininfluente. \n" +
                    "Esempio: /authors/search/all/0?id=1&nome=pippo&cognome=baudo",
            response = AutoreDTO.class,
            responseContainer = "List",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autore trovato"),
            @ApiResponse(code = 404, message = "Query string obbligatoria")
    })
    @GetMapping("/search")
    private ResponseEntity<List<AutoreDTO>> prova(@RequestParam(required = false) Integer id,
                                                  @RequestParam(required = false) String nome,
                                                  @RequestParam(required = false) String cognome) throws NotFoundException {

        if (id == null && nome == null && cognome == null) {
            throw new NotFoundException("La query string è obbligatoria");
        }

        List<AutoreDTO> autori = autoreService.searchByIdOrNameOrSurname(id, nome, cognome);

        return new ResponseEntity<List<AutoreDTO>>(autori, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Inserisce un autore se non è presente nel DB. L'id viene autogenerato",
            notes = "In caso di dati non validi consultare il messaggio d'errore")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Autore inserito nel DB"),
            @ApiResponse(code = 400, message = "Errore generico (comprende errori di binding)")
    })
    @PostMapping("/add")
    public ResponseEntity<AutoreDTO> insertAuthor(@RequestBody @Valid AutoreDTO autoreDTO,
                                                  BindingResult bindingResult) throws BindingException {

        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }

        autoreService.saveAuthor(autoreDTO);

        return new ResponseEntity<AutoreDTO>(HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Aggiornamento dell'autore tramite id",
            notes = "I dati vengono aggiornati solo se differiscono da quelli presenti nel DB e sono diversi da null")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autore aggiornato con successo"),
            @ApiResponse(code = 400, message = "Errore generico (comprende errori di binding)"),
            @ApiResponse(code = 404, message = "Autore non presente nel DB")
    })
    @PutMapping("/update")
    public ResponseEntity<AutoreDTO> updateAuthorById(@RequestBody @Valid AutoreDTO autoreDTO,
                                                      BindingResult bindingResult) throws BindingException, NotFoundException {

        if (bindingResult.hasErrors()) {
            String MsgErr = errMessage.getMessage(bindingResult.getFieldError(), LocaleContextHolder.getLocale());
            throw new BindingException(MsgErr);
        }

        autoreService.updateAuthor(autoreDTO);

        return new ResponseEntity<AutoreDTO>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "Elimina un autore dal DB tramite ID",
            notes = "Il record non può essere eliminato se presente come chiave esterna nell'entità Libro." +
                    "TO DO: Possibilità di eliminare Autore e sostituire con il placeholder ANONIMO")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Autore eliminato con successo"),
            @ApiResponse(code = 404, message = "Autore non presente nel DB")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AutoreDTO> deleteAuthorById(@PathVariable Integer id) throws NotFoundException {

        autoreService.deleteAuthorById(id);

        return new ResponseEntity<AutoreDTO>(HttpStatus.OK);
    }
}
