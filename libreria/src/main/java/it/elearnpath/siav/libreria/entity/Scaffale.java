package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "scaffale")
@Data
public class Scaffale {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer numero;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private Integer ripiano;

    @OneToMany(mappedBy = "posizioneBiblioteca", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "scaffale-libro")
    @JsonIgnore
    private List<Libro> libri;

}
