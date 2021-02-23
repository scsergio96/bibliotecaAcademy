package it.elearnpath.siav.libreria.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "scaffale")
@Data
public class Scaffale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numero;

    private Integer ripiano;

    @OneToMany(mappedBy = "scaffale", cascade = CascadeType.ALL)
    private List<Libro> libri;

}
