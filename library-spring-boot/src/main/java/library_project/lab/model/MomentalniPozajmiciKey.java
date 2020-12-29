package library_project.lab.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class MomentalniPozajmiciKey  implements Serializable {

    private Long seriskiBroj;
    private Long inventarenBroj;
    private ZonedDateTime datumPozajmuvanje;

    public MomentalniPozajmiciKey(){}
    public MomentalniPozajmiciKey(Long seriskiBroj, Long inventarenBroj, ZonedDateTime datumPozajmuvanje) {
        this.seriskiBroj = seriskiBroj;
        this.inventarenBroj = inventarenBroj;
        this.datumPozajmuvanje = datumPozajmuvanje;
    }
}
