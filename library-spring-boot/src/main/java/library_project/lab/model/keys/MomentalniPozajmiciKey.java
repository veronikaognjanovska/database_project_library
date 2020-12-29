package library_project.lab.model.keys;

import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public class MomentalniPozajmiciKey  implements Serializable {

    private Long seriskiBroj;
    private Long inventarenBroj;
    private String datumPozajmuvanje;

    public MomentalniPozajmiciKey(){}
    public MomentalniPozajmiciKey(Long seriskiBroj, Long inventarenBroj, String datumPozajmuvanje) {
        this.seriskiBroj = seriskiBroj;
        this.inventarenBroj = inventarenBroj;
        this.datumPozajmuvanje = datumPozajmuvanje;
    }
}
