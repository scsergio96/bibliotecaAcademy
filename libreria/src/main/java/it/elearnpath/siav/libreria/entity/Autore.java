package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "autore")
//@Data
public class Autore {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cognome;

    private Date dataNascita;

    private Date dataMorte;

    @Column(name = "nazionalità")
    private String nazionalita;

    private String biografia;

    @ManyToMany(mappedBy = "autori")
    @JsonManagedReference(value = "autore-libro")
    @JsonIgnore
    private List<Libro> libri;

    public Autore() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Date getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(Date dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    public void setLibri(List<Libro> libri) {
        this.libri = libri;
    }
}
