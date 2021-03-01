package it.elearnpath.siav.libreria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "libro")
// @Data
public class Libro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 100)
    private String titolo;

    @NotNull
    @Size(min = 1, max = 50)
    private String genere;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "casaEditrice", referencedColumnName = "id")
    @JsonBackReference(value = "casa-libro")
    private CasaEditrice casaEditrice;

    @NotNull
    @Size(min = 10, max = 20)
    private String isbn;

    @NotNull
    private Integer pagine;

    private Integer ristampa;

    @Size(max = 256)
    private String descrizione;

    @Size(max = 50)
    private String lingua;

    @Past
    private Date primaEdizione;

    @Past
    private Date ultimaRistampa;

    private Boolean isAvailable = true;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "posizioneBiblioteca", referencedColumnName = "id")
    @JsonBackReference(value = "scaffale-libro")
    private Scaffale posizioneBiblioteca;

    @ManyToMany
    @JoinTable(name = "autore_libro", joinColumns = @JoinColumn(name = "idLibro"), inverseJoinColumns = @JoinColumn(name = "idAutore"))
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

    public void setPrimaEdizione(String primaEdizione) {
        String pattern = "yyyy-MM-dd";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(primaEdizione);
            this.primaEdizione = date;
        } catch (Exception e) {
            // TODO: Creare error DateFormatException
            e.getMessage();
        }
    }

    public Date getUltimaRistampa() {
        return ultimaRistampa;
    }

    public void setUltimaRistampa(String ultimaRistampa) {
        String pattern = "yyyy-MM-dd";

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(ultimaRistampa);
            this.ultimaRistampa = date;
        } catch (Exception e) {
            // TODO: Creare error DateFormatException
            e.getMessage();
        }    }

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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
