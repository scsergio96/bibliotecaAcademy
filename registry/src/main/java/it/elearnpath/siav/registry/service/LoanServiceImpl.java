package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.LoanConverter;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.exception.NotFoundException;
import it.elearnpath.siav.registry.repository.LoanRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final RestTemplate restTemplate;

    public LoanServiceImpl(LoanRepository loanRepository, RestTemplate restTemplate) {
        this.loanRepository = loanRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId) {

        Loan loanExample = new Loan();
        loanExample.setIdReader(readerId);
        loanExample.setIdBook(bookId);

        List<Loan> loans = loanRepository.findAll(Example.of(loanExample));
        List<LoanDTO> loanDTOs = loans.stream()
                                      .map(LoanConverter::convert)
                                      .collect(Collectors.toList());
        return loanDTOs;
    }

    @Override
    public void save(LoanDTO loanDTO, Integer idReader) {

        loanDTO.setIdReader(idReader);

        Loan loan = LoanConverter.convert(loanDTO);

        loanRepository.save(loan);
    }

    @Override
    public LoanDTO updateLoanIfPresent(LoanDTO loanDTO) throws NotFoundException {

        Loan loan = LoanConverter.convert(loanDTO);
        Optional<Loan> oldLoan;

        if (loan.getId() != null) {
            oldLoan = loanRepository.findById(loan.getId());
        } else {
            throw new NotFoundException("Loan id cannot be null");
        }

        if (oldLoan.isPresent()) {
            loanRepository.save(loan);
            return LoanConverter.convert(loan);
        } else {
            throw new NotFoundException("Loan not present in the repository");
        }
    }
}
