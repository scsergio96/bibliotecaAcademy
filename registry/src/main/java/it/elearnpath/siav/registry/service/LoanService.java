package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.repository.LoanRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId) {
        Loan loanExample = new Loan();
        loanExample.setCardNumber(readerId);
        loanExample.setIdBook(bookId);

        List<Loan> loans = loanRepository.findAll(Example.of(loanExample));
        List<LoanDTO> loanDTOs = new ArrayList<>();

        loanDTOs = loans.stream()
            .map(loan -> {
                LoanDTO loanDTO = new LoanDTO();
                loanDTO.setId(loan.getId());
                loanDTO.setCardNumber(loan.getCardNumber());
                loanDTO.setIdBook(loan.getIdBook());
                loanDTO.setStart(loan.getStart());
                loanDTO.setEnd(loan.getEnd());
                return loanDTO;
            }).collect(Collectors.toList());

        return loanDTOs;
    }
}
