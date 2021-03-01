package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.BookDTO;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.entity.Reader;
import it.elearnpath.siav.registry.service.LoanService;
import it.elearnpath.siav.registry.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final ReaderService readerService;

    public LoanController(LoanService loanService, ReaderService readerService) {
        this.loanService = loanService;
        this.readerService = readerService;
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
    public ResponseEntity<LoanDTO> insertLoanByReaderCardNumberAndBookId(@RequestBody LoanDTO loanDTO) {

        RestTemplate restTemplate = new RestTemplate();

        Optional<BookDTO> bookResponse = Optional.empty();
        Optional<ReaderDTO> reader = Optional.empty();

        if (loanDTO.getIdBook() != null) {
            String urlBook = "http://localhost:8080/books/search/" + loanDTO.getIdBook();

            bookResponse = Optional.of(restTemplate.getForObject(urlBook, BookDTO.class));
        } // TODO else throw new BookNotFoundException

        if (loanDTO.getCardNumber() != null) {
            reader = Optional.of(readerService.findByCardNumber(loanDTO.getCardNumber()));
        } // TODO else throw new ReaderNotFoundException

        if (bookResponse.isPresent() && reader.isPresent()) {

            loanService.save(loanDTO, reader.get().getId());

            return new ResponseEntity<LoanDTO>(HttpStatus.OK);
        }

        return new ResponseEntity<LoanDTO>(HttpStatus.OK);
    }


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
    public ResponseEntity<BookDTO> provaProva() {
        RestTemplate restTemplate = new RestTemplate();

//        String url = "http://localhost:8080/authors/search/all/0";
        String urlBook = "http://localhost:8080/books/search/1";



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
//        Optional<AuthorDTO[]> response = Optional.of(restTemplate.getForObject(url, AuthorDTO[].class));
//
//        List<AuthorDTO> authors = new ArrayList<>();
//
//        if (response.isPresent()) {
//            authors = Arrays.asList(response.get());
//        }

        Optional<BookDTO> bookResponse = Optional.of(restTemplate.getForObject(urlBook, BookDTO.class));

        if (bookResponse.isPresent()) {
            ResponseEntity<BookDTO> bookDTOResponseEntity = new ResponseEntity<BookDTO>(bookResponse.get(), HttpStatus.OK);
            return bookDTOResponseEntity;
        }

        return new ResponseEntity<BookDTO>(HttpStatus.OK);
    }

}
