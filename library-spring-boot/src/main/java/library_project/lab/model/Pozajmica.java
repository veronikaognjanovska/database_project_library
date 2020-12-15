package library_project.lab.model;

import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Pozajmica implements Serializable {

    //???
    @Id @ManyToOne
    private Kniga seriski_broj;
    @Id @ManyToOne
    private Long inventaren_broj;


    @ManyToOne
    @JoinColumn(name="chlen_EMBG")
    private Chovek chlenEMBG;

    @ManyToOne
    @JoinColumn(name="vraboten_EMBG")
    private Chovek vrabotenEMBG;


    @ManyToOne
    @JoinColumn(name="datum_pozajmuvanje")
    private Date datumPozajmuvanje;

    @Column(name = "datum_vrakjanje")
    private Date datumVrakjanje;

    @Enumerated(EnumType.STRING)
    private STATUS_POZAJMICA status;

    public Pozajmica(){}


}
