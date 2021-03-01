package it.elearnpath.siav.registry.converter;

import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.entity.Loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanConverter {

    public static LoanDTO convert(Loan loan){

        if(loan == null){
            return null;
        }

        final LoanDTO loanDTO = new LoanDTO();

        loanDTO.setId(loan.getId());
        loanDTO.setIdBook(loan.getIdBook());
        loanDTO.setIdReader(loan.getIdReader());
        loanDTO.setStart(converterDateToString(loan.getStart()));
        loanDTO.setEnd(converterDateToString(loan.getEnd()));

        return loanDTO;
    }

    public static Loan convert(LoanDTO loanDTO) {

        if (loanDTO == null) {
            return null;
        }

        final Loan loan = new Loan();

        loan.setId(loanDTO.getId());
        loan.setIdBook(loanDTO.getIdBook());
        loan.setIdReader(loanDTO.getIdReader());
        loan.setStart(converterStringToDate(loanDTO.getStart()));
        loan.setEnd(converterStringToDate(loanDTO.getEnd()));

        return loan;
    }

    private static String converterDateToString(Date date) {
        if(date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date).toString();
        } else return null;
    }

    private static Date converterStringToDate(String date) {
        if(date != "" && date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }

}
