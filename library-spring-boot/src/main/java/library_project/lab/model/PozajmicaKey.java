package library_project.lab.model;

import com.sun.istack.NotNull;
import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Embeddable
@Data
public class PozajmicaKey  implements Serializable {

    @NotNull
    private Long seriski_broj;
    @NotNull
    private Long inventaren_broj;

    @ManyToOne
    @JoinColumn(name="chlen_EMBG")
    private Chlen chlenEMBG;

    @ManyToOne
    @JoinColumn(name="vraboten_EMBG")
    private Vraboten vrabotenEMBG;

    @NotNull
//    @JoinColumn(name="datum_pozajmuvanje")
    private ZonedDateTime datumPozajmuvanje;


    public PozajmicaKey(){}

    public PozajmicaKey(Long seriski_broj,Long inventaren_broj, Chlen chlenEMBG, Vraboten vrabotenEMBG, ZonedDateTime datumPozajmuvanje) {
        this.seriski_broj=seriski_broj;
        this.inventaren_broj=inventaren_broj;
        this.chlenEMBG = chlenEMBG;
        this.vrabotenEMBG = vrabotenEMBG;
        this.datumPozajmuvanje = datumPozajmuvanje;
    }


}
