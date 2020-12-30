package library_project.lab.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@Entity
public class Chlen extends Chovek implements Serializable {

    @Column(nullable = false)
    private ZonedDateTime datum_na_zachlenuvanje;

    public Chlen() { }

    public Chlen(String embg, String ime, String prezime, ZonedDateTime datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, ZonedDateTime datum_na_zachlenuvanje) {
        super(embg, ime, prezime, datum_na_ragjanje, adresa_na_ziveenje, telefonski_broj);
        this.datum_na_zachlenuvanje = datum_na_zachlenuvanje;
    }

    public String getImePrezime(){
        return String.format("%s %s",getIme(),getPrezime());
    }

}

