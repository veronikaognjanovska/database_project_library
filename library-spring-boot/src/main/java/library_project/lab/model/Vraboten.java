package library_project.lab.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Vraboten extends Chovek implements Serializable {


    @Column(nullable = false)
    private Date datum_na_vrabotuvanje;

    public Vraboten() { }

    public Vraboten(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, Date datum_na_vrabotuvanje) {
        super(embg, ime, prezime, datum_na_ragjanje, adresa_na_ziveenje, telefonski_broj);
        this.datum_na_vrabotuvanje = datum_na_vrabotuvanje;
    }
}
