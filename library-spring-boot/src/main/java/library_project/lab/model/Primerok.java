package library_project.lab.model;

import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
//@IdClass(PrimerokKey.class)
public class Primerok implements Serializable {

//    @Id
//    private Long seriski_broj;
//    @Id
//    @GeneratedValue(strategy= GenerationType.SEQUENCE)
//    private Long inventaren_broj;

    @EmbeddedId
    @JoinColumns(value = {
            @JoinColumn(name = "seriski_broj", referencedColumnName = "seriski_broj"),
            @JoinColumn(name = "inventaren_broj", referencedColumnName = "inventaren_broj")
    })
    private PrimerokKey primerokKey;


    @Enumerated(EnumType.STRING)
    private STATUS_PRIMEROK status;

    public Primerok(){}

    public Primerok(PrimerokKey primerokKey, STATUS_PRIMEROK status) {
        this.primerokKey=primerokKey;
        this.status = status;
    }
}
