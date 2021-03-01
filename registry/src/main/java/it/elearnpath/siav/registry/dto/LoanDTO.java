package it.elearnpath.siav.registry.dto;

import lombok.Data;

import java.util.Date;

import javax.validation.constraints.NotNull;

@Data
public class LoanDTO {

    @NotNull
    private Integer id;

    @NotNull
    private Integer idBook;

    @NotNull
    private Integer idReader;

    @NotNull
    private Date start;

    private Date end;

}
