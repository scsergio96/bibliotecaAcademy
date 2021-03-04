package it.elearnpath.siav.registry.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.elearnpath.siav.registry.dto.BookDTO;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
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

    @ApiOperation(
            value = "Search all the loans by matching readerId and/or bookId. Returns a void array if no loans are present",
            notes = "Research is done by query string (optional parameters, at least 1 is required)." +
                    "Request example: /loans/search?readerId=1&bookId=1",
            response = LoanDTO.class,
            responseContainer = "List",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The search was successful. (return a the list of Loan)"),
            @ApiResponse(code = 400, message = "The proper error is sent in the response's body")
    })
    @GetMapping("/search")
    public ResponseEntity<List<LoanDTO>> searchAllByReaderIdAndBookId(@RequestParam(required = false) Integer readerId,
                                                                      @RequestParam(required = false) Integer bookId)
            throws BadRequestException {

        if (readerId == null && bookId == null) {
            throw new BadRequestException("Query string required");
        }

        List<LoanDTO> loans = loanService.searchByReaderIdBookId(readerId, bookId);

        return new ResponseEntity<List<LoanDTO>>(loans, HttpStatus.OK);
    }

    @GetMapping("/search/open/{idBook}")
    public ResponseEntity<LoanDTO> searchPendingLoanByBookId(@PathVariable Integer idBook) throws BadRequestException {

        LoanDTO loanDTO = loanService.searchPendingLoanByBookId(idBook);

        return new ResponseEntity<LoanDTO>(loanDTO, HttpStatus.OK);
    }

    @ApiOperation(
            value = "Insert a new loan. The id value should be null, it is autogenerated.",
            notes = "The book and the reader must be present in the anagraphic server.",
            response = LoanDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Loan successfully created (return Loan created)"),
            @ApiResponse(code = 400, message = "The proper error message is sent in the response's body")
    })
    @PostMapping("/add")
    public ResponseEntity<LoanDTO> insertLoanByReaderCardNumberAndBookId(@RequestBody LoanDTO loanDTO)
            throws BadRequestException {

        LoanDTO loanInserted = loanService.insertLoanByValidReaderIdAndBookId(loanDTO);

        return new ResponseEntity<LoanDTO>(loanInserted, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Update the loan. A loan is considered closed when an end date is present in the record",
            notes = "The value will be modified if they don't match the record in the DB or are null",
            response = LoanDTO.class
    )
     @ApiResponses(value = {
             @ApiResponse(code = 200, message = "Loan updated successfully (return Loan updated)"),
             @ApiResponse(code = 400, message = "The proper error message is sent in the response's body")
     })
    @PutMapping("/mylittlepony")
    public ResponseEntity<LoanDTO> updateLoanIfPresent(@RequestBody LoanDTO loanDTO) throws BadRequestException {

        LoanDTO loanDTOUpdated = loanService.updateLoanIfPresent(loanDTO);

        return new ResponseEntity<LoanDTO>(loanDTOUpdated, HttpStatus.OK);

    }

}
