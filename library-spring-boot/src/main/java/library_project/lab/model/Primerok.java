package library_project.lab.model;

import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@IdClass(PrimerokKey.class)
public class Primerok implements Serializable {

    @Id
    private Long seriski_broj;
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long inventaren_broj;

    @Enumerated(EnumType.STRING)
    private STATUS_PRIMEROK status;

    public Primerok(){}

    public Primerok(Long inventaren_broj, STATUS_PRIMEROK status) {
        this.inventaren_broj = inventaren_broj;
        this.status = status;
    }
}
