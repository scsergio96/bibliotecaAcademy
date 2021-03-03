package it.elearnpath.siav.libreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import it.elearnpath.siav.libreria.converter.GenereDtoToGenere;
import it.elearnpath.siav.libreria.dto.GenereDTO;
import it.elearnpath.siav.libreria.entity.Genere;
import it.elearnpath.siav.libreria.service.GenereService;

@RestController
@RequestMapping(value = "/genres")
public class GenereController {

    @Autowired
    private GenereService genereService;

    @Autowired
    private GenereDtoToGenere genereDtoToGenere;

    @ApiOperation(value = "Restituisce una lista di generi", response = List.class, produces = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Tutto bene"),
            @ApiResponse(code = 400, message = "Errore generico") })
    @GetMapping(value = "/all")
    public ResponseEntity<List<Genere>> getAllGeneri() {

        List<Genere> generi = genereService.getGeneri();
        return new ResponseEntity<List<Genere>>(generi, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<GenereDTO> addGenere(@RequestBody GenereDTO genereDTO) {

        Genere genere = genereDtoToGenere.convert(genereDTO);
        genereService.addGenere(genere);

        return new ResponseEntity<GenereDTO>(HttpStatus.CREATED);
    }

}
