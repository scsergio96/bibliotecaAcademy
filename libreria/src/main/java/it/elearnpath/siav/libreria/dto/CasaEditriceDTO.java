package it.elearnpath.siav.libreria.dto;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "NotBlank.CasaEditrice.pIva.Validation")
    @Size(min = 11, max = 11, message = "La partita iva deve essere costituita da 11 numeri")
    private String pIva;

    @NotBlank(message = "NotBlank.CasaEditrice.ragioneSociale.Validation")
    @Size(min = 1, max = 100, message = "Size.CasaEditrice.ragioneSociale.Validation")
    private String ragioneSociale;

    @NotBlank(message = "NotBlank.CasaEditrice.indirizzo.Validation")
    @Size(max = 100, message = "Size.CasaEditrice.indirizzo.Validation")
    private String indirizzo;
}
