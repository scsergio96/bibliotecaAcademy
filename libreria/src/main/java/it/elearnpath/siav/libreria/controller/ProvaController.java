package it.elearnpath.siav.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.entity.Scaffale;
import it.elearnpath.siav.libreria.repository.AutoreRepository;
import it.elearnpath.siav.libreria.repository.CasaEditriceRepository;
import it.elearnpath.siav.libreria.repository.LibroRepository;
import it.elearnpath.siav.libreria.repository.ScaffaleRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
public class ProvaController {

    private final AutoreRepository autoreRepository;
    private final LibroRepository libroRepository;
    private final CasaEditriceRepository casaEditriceRepository;
    private final ScaffaleRepository scaffaleRepository;

    public ProvaController(AutoreRepository autoreRepository,
                           LibroRepository libroRepository,
                           CasaEditriceRepository casaEditriceRepository,
                           ScaffaleRepository scaffaleRepository) {
        this.autoreRepository = autoreRepository;
        this.libroRepository = libroRepository;
        this.casaEditriceRepository = casaEditriceRepository;
        this.scaffaleRepository = scaffaleRepository;
    }

    @GetMapping(value = "/autori")
    public ResponseEntity<List<Autore>> listaAutori() {

        List<Autore> autori = autoreRepository.findAll();

        return new ResponseEntity<List<Autore>>(autori, HttpStatus.OK);
    }

    @GetMapping(value = "/libri")
    public ResponseEntity<List<Libro>> listaLibri() {

        List<Libro> libri = libroRepository.findAll();

        return new ResponseEntity<List<Libro>>(libri, HttpStatus.OK);
    }

    @GetMapping(value = "/scaffali")
    public ResponseEntity<List<Scaffale>> listaScaffale() {

        List<Scaffale> scaffali = scaffaleRepository.findAll();

        return new ResponseEntity<List<Scaffale>>(scaffali, HttpStatus.OK);
    }

    @GetMapping(value = "/caseEditrici")
    public ResponseEntity<List<CasaEditrice>> listaCaseEditri() {

        List<CasaEditrice> caseEditrici = casaEditriceRepository.findAll();

        return new ResponseEntity<List<CasaEditrice>>(caseEditrici, HttpStatus.OK);
    }

    @PostMapping(value = "/autori/inserisci")
    public ResponseEntity<Autore> inseriscoAutore(@RequestBody @Valid Autore autore) {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = objectMapper.createObjectNode();

        autoreRepository.save(autore);

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message","Autore inserito");

        return new ResponseEntity<Autore>(headers, HttpStatus.CREATED);

    }

    @PostMapping(value = "/prova")
    public void prova() {

        Autore autore = new Autore();
        autore.setNome("ciao");
        autore.setCognome("belli");
        autore.setDataNascita(null);
        autore.setDataMorte(null);
        autore.setNazionalita("bulgaro");
        autore.setBiografia("ole");

        autoreRepository.save(autore);

    }

    @PostMapping(value = "/scaffali/inserisci")
    public ResponseEntity<Scaffale> inseriscoAutore(@RequestBody Scaffale scaffale) {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = objectMapper.createObjectNode();

        scaffaleRepository.save(scaffale);

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message","Autore inserito");

        return new ResponseEntity<Scaffale>(headers, HttpStatus.CREATED);

    } 
    
    @PostMapping(value = "/casaEditrice/inserisci")
    public ResponseEntity<CasaEditrice> inseriscoCasaEditrice(@RequestBody CasaEditrice casaEditrice) {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = objectMapper.createObjectNode();

        casaEditriceRepository.save(casaEditrice);

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message","Casa editrice inserita");

        return new ResponseEntity<CasaEditrice>(headers, HttpStatus.CREATED);

    }

    @PostMapping(value = "/libri/inserisci")
    public ResponseEntity<Libro> inseriscoLibro(@RequestBody @Valid Libro libro ) {

        HttpHeaders headers = new HttpHeaders();
        ObjectMapper objectMapper = new ObjectMapper();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectNode responseNode = objectMapper.createObjectNode();

        libroRepository.save(libro);

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message","Casa editrice inserita");

        return new ResponseEntity<Libro>(headers, HttpStatus.CREATED);

    }


}
