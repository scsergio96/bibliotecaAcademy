package it.elearnpath.siav.libreria.dto;

import java.util.List;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author Madalin Andrei Nadejde
 * @version 1.0
 */

 @Data
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

    List<LibroDTO> libri;
}
