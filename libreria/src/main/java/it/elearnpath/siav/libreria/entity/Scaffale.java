package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "posizioneBiblioteca", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "scaffale-libro")
    @JsonIgnore
    private List<Libro> libri;

}
