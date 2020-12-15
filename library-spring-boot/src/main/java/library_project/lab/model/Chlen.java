package library_project.lab.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class Chlen extends Chovek{

    @Column(nullable = false)
    private Date datum_na_zachlenuvanje;

    public Chlen() { }

    public Chlen(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, Date datum_na_zachlenuvanje) {
        super(embg, ime, prezime, datum_na_ragjanje, adresa_na_ziveenje, telefonski_broj);
        this.datum_na_zachlenuvanje = datum_na_zachlenuvanje;
    }

}
