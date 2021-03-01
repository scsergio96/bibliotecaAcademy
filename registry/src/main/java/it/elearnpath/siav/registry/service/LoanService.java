package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.exception.NotFoundException;

import java.util.List;



public interface LoanService {

    List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId);

    void save(LoanDTO loanDTO, Integer idReader);

    LoanDTO updateLoanIfPresent(LoanDTO loanDTO) throws NotFoundException;

}
