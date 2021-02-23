package it.elearnpath.siav.libreria.dto;

import lombok.Data;

@Data
public class LibroDTO {

    private Integer isbn;

    private String titolo;

    private Integer pagine;

    private String primaPubblicazione;

    private String ultimaStampa;

    private String descrizione;

    private String casaEditrice;

    private String genere;

    private String posizioneScaffale;

    private String lingua;

    private Integer idAutore;

    private String nomeAutore;

    private String cognomeAutore;

}
