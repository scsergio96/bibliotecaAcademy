package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "autore")
// @Data
public class Autore {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 50)
    private String nome;

    @NotNull
    @Size(max = 50)
    private String cognome;

    @Past
    private Date dataNascita;

    private Date dataMorte;

    @NotNull
    @Column(name = "nazionalità")
    private String nazionalita;

    @NotNull
    @Size(max = 256)
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

    public void setDataNascita(String dataNascita) {
        String pattern = "yyyy-MM-dd";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(dataNascita);
            this.dataNascita = date;
        } catch (Exception e) {
            // TODO: Creare error DateFormatException
            e.getMessage();
        }
    }

    public Date getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(String dataMorte) {
        String pattern = "yyyy-MM-dd";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(dataMorte);
            this.dataMorte = date;
        } catch (Exception e) {
            // TODO: Creare error DateFormatException
            e.getMessage();
        }
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
