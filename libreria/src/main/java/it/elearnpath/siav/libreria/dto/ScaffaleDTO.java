package it.elearnpath.siav.libreria.dto;

import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ScaffaleDTO {

    @Id
    private Integer id;

    @NotNull
    private Integer numero;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer ripiano;

    

}