package it.elearnpath.siav.libreria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountGenresDTO {

    private Integer id;
    private String count;
    private String genre;
    
}
