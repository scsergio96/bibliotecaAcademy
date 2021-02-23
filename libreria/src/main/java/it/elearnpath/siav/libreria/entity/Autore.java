package it.elearnpath.siav.libreria.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "autore")
@Data
public class Autore {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cognome;

    private Date dataNascita;

    private Date dataMorte;

    private String nazionalita;

    private String biografia;

    @ManyToMany(mappedBy = "autori")
    private List<Libro> libri;

}
