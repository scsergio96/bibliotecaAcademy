package it.elearnpath.siav.libreria.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class LibroDTO {

    private Integer id;

    private String isbn;

    private String titolo;

    private Integer pagine;

    private String primaPubblicazione;

    private String ultimaStampa;

    private String descrizione;

    private String casaEditrice;

    private String genere;

    private Integer posizione;

    private Integer ripiano;

    private String lingua;

    private List<Integer> idAutore;

    private List<String> nomeAutore;

    private List<String> cognomeAutore;

    private Boolean isAvailable;

}
