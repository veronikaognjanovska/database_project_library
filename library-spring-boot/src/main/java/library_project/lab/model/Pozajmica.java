package library_project.lab.model;

import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.ZonedDateTime;

@Data
@Entity
//@IdClass(PozajmicaKey.class)
public class Pozajmica implements Serializable {


//    @MapsId("seriski_broj")
//    @ManyToOne(optional = false)
//    @JoinColumns(value = {
//            @JoinColumn(name = "seriski_broj", referencedColumnName = "seriski_broj"),
//            @JoinColumn(name = "inventaren_broj", referencedColumnName = "inventaren_broj")
//    })
//    private Primerok entityPrimerok;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name="chlen_EMBG")
//    private Chovek chlenEMBG;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name="vraboten_EMBG")
//    private Chovek vrabotenEMBG;
//
//    @Id
//    @JoinColumn(name="datum_pozajmuvanje")
//    private ZonedDateTime datumPozajmuvanje;


    @EmbeddedId
    @JoinColumns(value = {
            @JoinColumn(name = "seriski_broj", referencedColumnName = "seriski_broj"),
            @JoinColumn(name = "inventaren_broj", referencedColumnName = "inventaren_broj"),
            @JoinColumn(name = "chlenEMBG", referencedColumnName = "chlen_embg"),
            @JoinColumn(name = "vrabotenEMBG", referencedColumnName = "vraboten_embg"),
            @JoinColumn(name = "datumPozajmuvanje", referencedColumnName = "datum_pozajmuvanje")
    })
    private PozajmicaKey pozajmicaKey;


    @Column(name = "datum_vrakjanje")
    private ZonedDateTime datumVrakjanje;

    @Enumerated(EnumType.STRING)
    private STATUS_POZAJMICA status;

    public Pozajmica(){}

    public Pozajmica(Long seriski_broj,Long inventaren_broj, Chlen chlenEMBG, Vraboten vrabotenEMBG, ZonedDateTime datumPozajmuvanje, STATUS_POZAJMICA status) {
        this.pozajmicaKey = new PozajmicaKey( seriski_broj, inventaren_broj,chlenEMBG,vrabotenEMBG,datumPozajmuvanje);
        this.status = status;
    }
}
