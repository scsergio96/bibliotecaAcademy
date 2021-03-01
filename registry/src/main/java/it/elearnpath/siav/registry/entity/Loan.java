package it.elearnpath.siav.registry.entity;

import lombok.Data;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Data
/**
 * classe che definisce l'azione di prendere in prestito un libro da parte di una persona
 * (si tratta di una relazione molti a molti)
 */
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer idBook;

    @NotNull
    private Integer idReader;

    @NotNull
    private Date start;

    private Date end;

}
