package it.elearnpath.siav.libreria.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AutoreDTO {

    @Min(value = 1, message = "{Min.Autore.id.Validation}")
    private Integer id;

    @NotBlank(message = "{NotBlank.Autore.nome.Validation}")
    @Size(max = 50, message = "{Size.Autore.nome.Validation}")
    private String nome;

    @NotBlank(message = "{NotBlank.Autore.nome.Validation}")
    @Size(max = 50, message = "{Size.Autore.nome.Validation}")
    private String cognome;

    @NotBlank(message = "{NotBlank.Autore.biografia.Validation}")
    @Size(max = 256, message = "{Size.Autore.biografia.Validation}")
    private String biografia;


    private String dataNascita;

    private String dataMorte;

    @NotBlank(message = "{NotBlank.Autore.nazionalita.Validation}")
    private String nazionalita;
}
