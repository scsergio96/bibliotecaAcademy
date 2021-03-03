package it.elearnpath.siav.registry.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ReaderDTO {

    private Integer id;

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size(max = 50)
    @NotBlank
    private String surname;

    private Integer cardNumber;

    private String address;

}
