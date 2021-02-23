package it.elearnpath.siav.libreria.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "libro")
@Data
public class Libro {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    private String titolo;

    private String genere;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "casaEditrice_id", referencedColumnName = "id")
    private CasaEditrice casaEditrice;

    private Integer isbn;

    private Integer pagine;

    private Integer ristampa;

    private String descrizione;

    private String lingua;

    private Date annoPrimaEdizione;

    private Date annoUltimaRistampa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scaffale_id", referencedColumnName = "id")
    private Scaffale scaffale;

    @ManyToMany
    @JoinTable(
            name = "autore_libro",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autore_id"))
    private List<Autore> autori;

}
