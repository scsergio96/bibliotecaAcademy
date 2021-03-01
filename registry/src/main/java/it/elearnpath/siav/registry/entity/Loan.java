package it.elearnpath.siav.registry.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Loan {

    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @NotNull
    private Integer idBook;

    @NotNull
    private Integer cardNumber;

    private Date start;

    private Date end;

}
