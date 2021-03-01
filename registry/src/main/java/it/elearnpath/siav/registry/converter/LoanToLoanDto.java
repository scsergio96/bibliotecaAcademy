package it.elearnpath.siav.registry.converter;

import org.springframework.context.annotation.Configuration;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;

@Configuration
public class LoanToLoanDto {

    public LoanDTO convert(Loan loan){

        if(loan == null){
            return null;
        }

        final LoanDTO loanDTO = new LoanDTO();

        loanDTO.setId(loan.getId());
        loanDTO.setIdBook(loan.getIdBook());
        loanDTO.setIdReader(loan.getIdReader());
        loanDTO.setStart(loan.getStart());
        loanDTO.setEnd(loan.getEnd());

        return loanDTO;
    }
    
}
