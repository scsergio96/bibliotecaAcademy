package it.elearnpath.siav.registry.service;

import java.util.List;



public interface LoanService {

    List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId);

    void save(LoanDTO loanDTO, Integer idReader);
}
