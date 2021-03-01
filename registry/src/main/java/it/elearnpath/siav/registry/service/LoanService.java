package it.elearnpath.siav.registry.service;

import java.util.List;

import it.elearnpath.siav.registry.dto.LoanDTO;

public interface LoanService {

    List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId);
    
}
