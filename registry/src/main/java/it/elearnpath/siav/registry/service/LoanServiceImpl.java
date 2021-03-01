package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.LoanToLoanDto;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.repository.LoanRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final LoanToLoanDto loanToLoanDto;

    public LoanServiceImpl(LoanRepository loanRepository, LoanToLoanDto loanToLoanDto) {
        this.loanRepository = loanRepository;
        this.loanToLoanDto = loanToLoanDto;
    }

    @Override
    public List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId) {

        Loan loanExample = new Loan();
        loanExample.setIdReader(readerId);
        loanExample.setIdBook(bookId);

        List<Loan> loans = loanRepository.findAll(Example.of(loanExample));
        List<LoanDTO> loanDTOs = loans.stream()
                                      .map(loan -> loanToLoanDto.convert(loan))
                                      .collect(Collectors.toList());
        return loanDTOs;
    }
}
