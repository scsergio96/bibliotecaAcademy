package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.LibraryCardDTO;
import it.elearnpath.siav.registry.exception.BadRequestException;
import it.elearnpath.siav.registry.service.LibraryCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/library/card")
public class LibraryCardController {

    private final LibraryCardService libraryCardService;

    public LibraryCardController(LibraryCardService libraryCardService) {
        this.libraryCardService = libraryCardService;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<LibraryCardDTO> searchLibraryCardById(@PathVariable Integer id) throws BadRequestException {

        LibraryCardDTO libraryCardDTO = libraryCardService.searchCardById(id);

        return new ResponseEntity<LibraryCardDTO>(libraryCardDTO, HttpStatus.OK);

    }

}
