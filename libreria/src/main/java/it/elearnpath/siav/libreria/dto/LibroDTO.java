package it.elearnpath.siav.libreria.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LibroDTO {

    @Min(value = 1, message = "{Min.Libro.id.Validation}")
    private Integer id;

    @NotBlank(message = "{NotBlank.Libro.isbn.Validation}")
    @Size(min = 10, max = 20, message = "{Size.Libro.isbn.Validation}")
    private String isbn;

    @NotBlank(message = "{NotBlank.Libro.titolo.Validation}")
    @Size(max = 100, message = "{Size.Libro.titolo.Validation}")
    private String titolo;

    @NotNull(message = "{NotNull.Libro.pagine.Validation}")
    private Integer pagine;

    private String primaPubblicazione;

    private String ultimaStampa;
    
    @NotBlank(message = "{NotBlank.Libro.descrizione.Validation}")
    @Size(max = 256, message = "{Size.Libro.descrizione.Validation}")
    private String descrizione;

    private String casaEditrice;

    private GenereDTO genere;

    private Integer ristampa;

    private Integer posizione;
    
    @Min(value = -1, message = "{Min.Libro.ripiano.Validation}")
    @Max(value = 5 , message = "{Max.Libro.ripiano.Validation}")
    private Integer ripiano;
    
    @NotNull(message = "{NotNull.Libro.lingua.Validation}")
    @Size(max = 50, message = "{Size.Libro.lingua.Validation}")
    private String lingua;

    private List<Integer> idAutore;

    private List<String> nomeAutore;

    private List<String> cognomeAutore;

    private Boolean isAvailable = true;

}
