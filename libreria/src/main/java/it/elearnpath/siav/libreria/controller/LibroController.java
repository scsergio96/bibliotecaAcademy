package it.elearnpath.siav.libreria.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.elearnpath.siav.libreria.converter.LibroDtoToLibro;
import it.elearnpath.siav.libreria.converter.LibroToLibroDto;
import it.elearnpath.siav.libreria.dto.LibroDTO;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.services.LibroService;

@RestController
@RequestMapping("/books")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private LibroToLibroDto libroToLibroDto;

    @Autowired
    private LibroDtoToLibro libroDtoToLibro;

    /**
     * @param id da cercare
     * @return LibroDTO ricercato per Id
     * @throws NotFoundException se l'elemento non è presente su DB
     */
    @GetMapping(value = "/search/{id}")
    public ResponseEntity<LibroDTO> searchById(@PathVariable("id") Integer id) throws NotFoundException {

        if (!libroService.getLibro(id).isPresent()) {
            String errMsg = String.format("Il libro con codice %s non è stato trovato!", id);
            throw new NotFoundException(errMsg);
        }

        Libro libro = libroService.getLibro(id).get();
        return new ResponseEntity<LibroDTO>(libroToLibroDto.convert(libro), HttpStatus.OK);

    }

    /**
     * @param page da 20 libri
     * @return List<LibroDTO> lista di libri ordinati per titolo in ordine
     *         alfabetico
     * @throws NotFoundException se la pagina non esiste
     */
    @GetMapping(value = "/search/page/{page}")
    public ResponseEntity<List<LibroDTO>> searchAll(@PathVariable("page") Integer page) throws NotFoundException {

        if (libroService.getLibri(PageRequest.of(page, 20)).isEmpty()) {
            String errMsg = String.format("La pagina %s non esiste!", page);
            throw new NotFoundException(errMsg);
        }

        List<LibroDTO> libri = (libroService.getLibri(PageRequest.of(page, 20))).stream()
                .map(libro -> libroToLibroDto.convert(libro)).collect(Collectors.toList());
        return new ResponseEntity<List<LibroDTO>>(libri, HttpStatus.OK);

    }

    @PostMapping(value = "/add")
    public ResponseEntity<LibroDTO> insertLibro(@RequestBody LibroDTO libroDTO) {
        Libro libro = libroDtoToLibro.convert(libroDTO);
        libroService.insLibro(libro);
        return new ResponseEntity<LibroDTO>(HttpStatus.OK);
    } 



}
