package it.elearnpath.siav.registry.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LibraryCardDTO {

    private Integer id;

    private String expiration;

    private boolean isValid = true;

}
