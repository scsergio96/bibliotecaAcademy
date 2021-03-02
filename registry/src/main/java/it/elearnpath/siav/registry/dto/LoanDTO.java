package it.elearnpath.siav.registry.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoanDTO {

    private Integer id;

    @NotNull
    private Integer idBook;

    @NotNull
    private Integer cardNumber;

    private Integer idReader;

    @NotBlank
    private String start;

    private String end;

}
