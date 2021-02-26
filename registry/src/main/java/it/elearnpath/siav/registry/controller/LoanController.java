package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
