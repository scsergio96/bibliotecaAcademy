package it.elearnpath.siav.libreria.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "casa_editrice")
@Data
public class CasaEditrice {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pIva;

    private String ragioneSociale;

    private String indirizzo;

    @OneToMany(mappedBy = "casaEditrice", cascade = CascadeType.ALL)
    private List<Libro> libri;

}
