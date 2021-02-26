package it.elearnpath.siav.libreria.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.annotations.ApiResponse;

import it.elearnpath.siav.libreria.converter.LibroDtoToLibro;
import it.elearnpath.siav.libreria.converter.LibroToLibroDto;
import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.LibroService;

@RestController
@RequestMapping("/books")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroToLibroDto libroToLibroDto;

    @Autowired
    private LibroDtoToLibro libroDtoToLibro;

    @ApiOperation(value = "Ricerca libro per id", notes = "I dati sono restituiti in formato JSON", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/search/{id}")
    public ResponseEntity<LibroDTO> searchById(@PathVariable("id") Integer id) throws NotFoundException {

        if (!libroService.getLibro(id).isPresent()) {
            String errMsg = String.format("Il libro con codice %s non è stato trovato!", id);
            throw new NotFoundException(errMsg);
        }

        Libro libro = libroService.getLibro(id).get();
        return new ResponseEntity<LibroDTO>(libroToLibroDto.convert(libro), HttpStatus.OK);

    }

    @ApiOperation(value = "Ricerca libro per isbn", notes = "I dati sono restituiti in formato JSON", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/search/isbn")
    public ResponseEntity<LibroDTO> searchByIsbn(@RequestParam(required = false) String isbn) throws NotFoundException {

        Libro libro = libroService.getLibroByIsbn(isbn).get();
        return new ResponseEntity<LibroDTO>(libroToLibroDto.convert(libro), HttpStatus.OK);

    }

    @ApiOperation(value = "Ricerca libro per id", notes = "I dati sono restituiti in formato JSON", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/search2")
    public ResponseEntity<List<LibroDTO>> searchByIdOrIsbnOrTitolo(@RequestParam(required = false) Integer id,
            @RequestParam(required = false) String isbn, @RequestParam(required = false) String titolo)
            throws NotFoundException {

        List<Libro> libri = libroService.getLibroByIdOrIsbnOrTitolo(id, isbn, titolo);

        List<LibroDTO> libriDTO = libri.stream().map(libro -> libroToLibroDto.convert(libro))
                .collect(Collectors.toList());

        return new ResponseEntity<List<LibroDTO>>(libriDTO, HttpStatus.OK);

    }

    @ApiOperation(value = "Ricerca tutti i libri presenti nel DB", notes = "I risultati son restituite in pagine da 10. La numerazione delle pagine inizia da 0. "
            + "I dati sono restituiti in formato JSON", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/search/page/{page}")
    public ResponseEntity<List<LibroDTO>> searchAll(@PathVariable("page") Integer page) throws NotFoundException {

        if (libroService.getLibri(PageRequest.of(page, 10)).isEmpty()) {
            String errMsg = String.format("La pagina %s non esiste!", page);
            throw new NotFoundException(errMsg);
        }

        List<LibroDTO> libri = (libroService.getLibri(PageRequest.of(page, 10))).stream()
                .map(libro -> libroToLibroDto.convert(libro)).collect(Collectors.toList());

        return new ResponseEntity<List<LibroDTO>>(libri, HttpStatus.OK);

    }

    @ApiOperation(value = "Inserisce un libro se non è presente nel DB", notes = "I dati dell'autore vengono prelevati dal body della request", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Libro inserito nel DB"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @PostMapping(value = "/add")
    public ResponseEntity<LibroDTO> insertLibro(@RequestBody LibroDTO libroDTO) {
        Libro libro = libroDtoToLibro.convert(libroDTO);
        libroService.insLibro(libro);

        return new ResponseEntity<LibroDTO>(HttpStatus.OK);
    }

    @ApiOperation(value = "Aggiornamento del libro tramite id", notes = "I dati vengono aggiornati solo se differiscono da quelli presenti nel DB o sono diversi da null", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @PutMapping(value = "/update")
    public ResponseEntity<LibroDTO> updateLibro(@RequestBody LibroDTO libroDTO) throws NotFoundException {
        Libro libro = libroDtoToLibro.convertWithId(libroDTO);
        if (!libroService.getLibro(libro.getId()).isPresent()) {
            String errMsg = String.format("Il libro con codice %s non è stato trovato!", libro.getId());
            throw new NotFoundException(errMsg);
        }
        libroService.insLibro(libro);

        return new ResponseEntity<LibroDTO>(HttpStatus.OK);

    }

    @ApiOperation(value = "Elimina un libro tramite id", notes = "L'eliminazione va a buon fine solo se il libro è presente sul DB", response = LibroDTO.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<LibroDTO> deleteLibro(@PathVariable Integer id) throws NotFoundException {

        if (!libroService.getLibro(id).isPresent()) {
            String errMsg = String.format("Il libro con codice %s non è stato trovato!", id);
            throw new NotFoundException(errMsg);
        }

        libroService.delLibro(id);

        return new ResponseEntity<LibroDTO>(HttpStatus.OK);
    }

    @ApiOperation(value = "Restituisce una lista di generi", response = List.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/genres")
    public ResponseEntity<List<String>> showGenres() {
        List<String> genres = libroService.getGenres();
        return new ResponseEntity<List<String>>(genres, HttpStatus.OK);
    }

}
