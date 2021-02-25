package it.elearnpath.siav.libreria.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author Madalin Andrei Nadejde
 * @version 1.0
 */

 @Data
 @JsonIgnoreProperties
public class CasaEditriceDTO{

    @Id
    private Integer id;

    @NotNull
    @Size(min = 10, max = 20)
    private String pIva;

    @NotNull
    @Size(max = 100)
    private String ragioneSociale;

    @NotNull
    @Size(max = 100)
    private String indirizzo;
}
