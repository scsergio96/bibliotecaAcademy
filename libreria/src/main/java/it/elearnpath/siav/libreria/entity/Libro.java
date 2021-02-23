package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "libro")
//@Data
public class Libro {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    private String titolo;

    private String genere;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "casaEditrice", referencedColumnName = "id")
    @JsonBackReference(value = "casa-libro")
    private CasaEditrice casaEditrice;

    private Integer isbn;

    private Integer pagine;

    private Integer ristampa;

    private String descrizione;

    private String lingua;

    private Date primaEdizione;

    private Date ultimaRistampa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "posizioneBiblioteca", referencedColumnName = "id")
    @JsonBackReference(value = "scaffale-libro")
    private Scaffale posizioneBiblioteca;

    @ManyToMany
    @JoinTable(
            name = "autore_libro",
            joinColumns = @JoinColumn(name = "idLibro"),
            inverseJoinColumns = @JoinColumn(name = "idAutore"))
    @JsonBackReference(value = "autore-libro")
    private List<Autore> autori;

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public CasaEditrice getCasaEditrice() {
        return casaEditrice;
    }

    public void setCasaEditrice(CasaEditrice casaEditrice) {
        this.casaEditrice = casaEditrice;
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public Integer getPagine() {
        return pagine;
    }

    public void setPagine(Integer pagine) {
        this.pagine = pagine;
    }

    public Integer getRistampa() {
        return ristampa;
    }

    public void setRistampa(Integer ristampa) {
        this.ristampa = ristampa;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public Date getPrimaEdizione() {
        return primaEdizione;
    }

    public void setPrimaEdizione(Date primaEdizione) {
        this.primaEdizione = primaEdizione;
    }

    public Date getUltimaRistampa() {
        return ultimaRistampa;
    }

    public void setUltimaRistampa(Date ultimaRistampa) {
        this.ultimaRistampa = ultimaRistampa;
    }

    public Scaffale getPosizioneBiblioteca() {
        return posizioneBiblioteca;
    }

    public void setPosizioneBiblioteca(Scaffale posizioneBiblioteca) {
        this.posizioneBiblioteca = posizioneBiblioteca;
    }

    public List<Autore> getAutori() {
        return autori;
    }

    public void setAutori(List<Autore> autori) {
        this.autori = autori;
    }
}
