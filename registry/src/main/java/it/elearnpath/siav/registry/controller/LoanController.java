package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.BookDTO;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.exception.BadRequestException;
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
    private final RestTemplate restTemplate;

    public LoanController(LoanService loanService, ReaderService readerService, RestTemplate restTemplate) {
        this.loanService = loanService;
        this.readerService = readerService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/search")
    public ResponseEntity<List<LoanDTO>> searchAllByReaderIdAndBookId(@RequestParam(required = false) Integer readerId,
                                                                      @RequestParam(required = false) Integer bookId)
            throws BadRequestException {

        if (readerId == null && bookId == null) {
            throw new BadRequestException("Query string obbligatoria");
        }

        List<LoanDTO> loans = loanService.searchByReaderIdBookId(readerId, bookId);

        return new ResponseEntity<List<LoanDTO>>(loans, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<LoanDTO> insertLoanByReaderCardNumberAndBookId(@RequestBody LoanDTO loanDTO)
            throws BadRequestException {

        LoanDTO loanInserted = loanService.insertLoanByValidReaderCardNumberAndBookId(loanDTO);

        return new ResponseEntity<LoanDTO>(loanInserted, HttpStatus.OK);
    }

    @PutMapping("/mylittlepony")
    public ResponseEntity<LoanDTO> updateLoanIfPresent(@RequestBody LoanDTO loanDTO) throws BadRequestException {

        LoanDTO loanDTOUpdated = loanService.updateLoanIfPresent(loanDTO);

        return new ResponseEntity<LoanDTO>(loanDTOUpdated, HttpStatus.OK);

    }

}
