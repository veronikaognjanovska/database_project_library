package library_project.lab.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Chovek implements Serializable {

    @Id @Column(length = 13)
    private String embg;

    @Column(nullable = false)
    private String ime;
    @Column(nullable = false)
    private String prezime;
    @Column(nullable = false)
    private Date datum_na_ragjanje;
    @Column(nullable = false)
    private String adresa_na_ziveenje;
    @Column(nullable = false)
    private String telefonski_broj;

    public Chovek() { }

    public Chovek(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj) {
        this.embg = embg;
        this.ime = ime;
        this.prezime = prezime;
        this.datum_na_ragjanje = datum_na_ragjanje;
        this.adresa_na_ziveenje = adresa_na_ziveenje;
        this.telefonski_broj = telefonski_broj;
    }
}
