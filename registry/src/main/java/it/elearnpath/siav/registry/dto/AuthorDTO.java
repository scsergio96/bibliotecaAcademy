package it.elearnpath.siav.registry.dto;

import lombok.Data;

@Data
public class AuthorDTO {

    private Integer id;

    private String nome;

    private String cognome;

    private String biografia;

    private String dataNascita;

    private String dataMorte;

    private String nazionalita;

}
