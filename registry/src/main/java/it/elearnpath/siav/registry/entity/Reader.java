package it.elearnpath.siav.registry.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size(max = 50)
    @NotBlank
    private String surname;

    @NotNull
    private Integer cardNumber;

    private String address;

}
