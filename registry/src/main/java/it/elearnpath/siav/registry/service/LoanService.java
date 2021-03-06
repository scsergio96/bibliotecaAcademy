package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.exception.BadRequestException;
import it.elearnpath.siav.registry.exception.NotFoundException;

import java.util.List;



public interface LoanService {

    List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId);

    LoanDTO updateLoanIfPresent(LoanDTO loanDTO) throws BadRequestException;

//    LoanDTO insertLoanByValidReaderCardNumberAndBookId(LoanDTO loanDTO) throws BadRequestException;

    LoanDTO insertLoanByValidReaderIdAndBookId(LoanDTO loanDTO) throws BadRequestException;

    LoanDTO searchPendingLoanByBookId(Integer idBook) throws BadRequestException;

    LoanDTO sagaInsertLoan(LoanDTO loanDTO) throws BadRequestException;

    void sagaInsertLoanRollback(Integer id);
}
