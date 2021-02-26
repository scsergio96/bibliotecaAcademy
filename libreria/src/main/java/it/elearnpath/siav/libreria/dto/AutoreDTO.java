package it.elearnpath.siav.libreria.dto;

import lombok.Data;
import org.springframework.data.domain.ExampleMatcher;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Data
public class AutoreDTO {

    @Min(value = 1, message = "{Min.Autore.id.Validation}")
    private Integer id;

    @NotNull(message = "{NotNull.Autore.nome.Validation}")
    @Size(max = 50, message = "{Size.Autore.nome.Validation}")
    private String nome;

    @NotNull(message = "{NotNull.Autore.nome.Validation}")
    @Size(max = 50, message = "{Size.Autore.nome.Validation}")
    private String cognome;

    @NotNull(message = "{NotNull.Autore.biografia.Validation}")
    @Size(max = 256, message = "{Size.Autore.biografia.Validation}")
    private String biografia;


    private String dataNascita;

    private String dataMorte;

    @NotNull(message = "{NotNull.Autore.nazionalita.Validation}")
    private String nazionalita;
}
