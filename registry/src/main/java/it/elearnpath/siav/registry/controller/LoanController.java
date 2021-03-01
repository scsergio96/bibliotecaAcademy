package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.AuthorDTO;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.service.LoanService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanDTO>> searchAllByReaderIdAndBookId(@RequestParam(required = false) Integer readerId,
                                                                      @RequestParam(required = false) Integer bookId) throws Exception {

        if (readerId == null && bookId == null) {
            throw new Exception("Query string obbligatoria");
        }

        List<LoanDTO> loans = loanService.searchByReaderIdBookId(readerId, bookId);

        return new ResponseEntity<List<LoanDTO>>(loans, HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity<LoanDTO> addLoanByReaderIdAndBookIdNoCheck(@RequestBody)

//    @PostMapping("/add")
//    public ResponseEntity addNewLoan(@RequestBody Integer bookId) {
//
//        if (bookId != null) {
//            loanService.addNewLoan(bookId);
//        }
//
//        return new ResponseEntity(HttpStatus.OK);
//
//    }

    @GetMapping("/prova/prova")
    public ResponseEntity<List<AuthorDTO>> provaProva() {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/authors/search/all/0";



//        WebClient webClient = WebClient.builder()
//                .baseUrl("http://localhost:8080")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .build();
//
//        Flux<AuthorDTO> authorDTOFlux = webClient.get()
//                .uri("/authors/search/all/0")
//                .retrieve()
//                .bodyToFlux(AuthorDTO.class);
//
//
        Optional<AuthorDTO[]> response = Optional.of(restTemplate.getForObject(url, AuthorDTO[].class));

        List<AuthorDTO> authors = new ArrayList<>();

        if (response.isPresent()) {
            authors = Arrays.asList(response.get());
        }

        return new ResponseEntity<List<AuthorDTO>>(authors, HttpStatus.OK);
    }

}
