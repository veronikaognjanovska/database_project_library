package library_project.lab.model;

import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
public class Pozajmica implements Serializable {



    @MapsId("seriski_broj")
    @ManyToOne(optional = false)
    @JoinColumns(value = {
            @JoinColumn(name = "seriski_broj", referencedColumnName = "seriski_broj"),
            @JoinColumn(name = "inventaren_broj", referencedColumnName = "inventaren_broj")
    })
    private Primerok entityPrimerok;

    @Id
    @ManyToOne
    @JoinColumn(name="chlen_EMBG")
    private Chovek chlenEMBG;

    @Id
    @ManyToOne
    @JoinColumn(name="vraboten_EMBG")
    private Chovek vrabotenEMBG;

    @Id
    @JoinColumn(name="datum_pozajmuvanje")
    private Date datumPozajmuvanje;

    @Column(name = "datum_vrakjanje")
    private Date datumVrakjanje;

    @Enumerated(EnumType.STRING)
    private STATUS_POZAJMICA status;

    public Pozajmica(){}


}
