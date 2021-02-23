package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "casa_editrice")
@Data
public class CasaEditrice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 10, max = 20)
    private String pIva;

    @NotNull
    @Size(max = 100)
    private String ragioneSociale;

    @NotNull
    @Size(max = 100)
    private String indirizzo;

    @OneToMany(mappedBy = "casaEditrice", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "casa-libro")
    @JsonIgnore
    private List<Libro> libri;

}
