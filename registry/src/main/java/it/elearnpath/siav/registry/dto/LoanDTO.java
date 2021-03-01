package it.elearnpath.siav.registry.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class LoanDTO {

    @NotNull
    private Integer id;

    @NotNull
    private Integer idBook;

    @NotNull
    private Integer cardNumber;

    private Date start;

    private Date end;

}
