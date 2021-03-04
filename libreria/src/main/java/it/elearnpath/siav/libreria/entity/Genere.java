package it.elearnpath.siav.libreria.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "genere")
@Data
public class Genere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 45)
    private String genere;
    
    @OneToMany(mappedBy = "genere", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "genere-libro")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Libro> libri;

}
