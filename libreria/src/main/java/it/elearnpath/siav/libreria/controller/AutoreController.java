package it.elearnpath.siav.libreria.controller;

import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.service.AutoreService;
import org.springframework.http.HttpStatus;
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


    @GetMapping("/search/all/{page}")
    public ResponseEntity<List<AutoreDTO>> getAllAuthorsPaging(@PathVariable("page") int page) {
        List<AutoreDTO> autori = autoreService.findAllPaging(page);
        return new ResponseEntity<List<AutoreDTO>>(autori, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<AutoreDTO> getAuthorById(@PathVariable("id") Integer id) throws NotFoundException {
        AutoreDTO autoreDTO = autoreService.getAuthorById(id);
        return new ResponseEntity<AutoreDTO>(autoreDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AutoreDTO> insertAuthor(@RequestBody AutoreDTO autoreDTO) {
        autoreService.saveAuthor(autoreDTO);
        return new ResponseEntity<AutoreDTO>(HttpStatus.CREATED);
    }

    //TODO finire questo schifo
    @PutMapping("/update")
    public ResponseEntity<AutoreDTO> updateAuthorById(@RequestBody AutoreDTO autoreDTO) throws Exception {
        autoreService.updateAuthor(autoreDTO);
        return new ResponseEntity<AutoreDTO>(HttpStatus.OK);
    }

}
