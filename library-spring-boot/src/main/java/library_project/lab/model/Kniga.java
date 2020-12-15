package library_project.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Kniga implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long seriski_broj;

    @Column(nullable = false)
    private String naslov;

    @Column(name = "broj_strani")
    private Integer brojStrani;

    @ManyToOne
    @JoinColumn(name="nastan_id")
    private Nastan nastan;

    public Kniga() {}

    public Kniga(String naslov, Integer broj_strani, Nastan nastan_id) {
        this.naslov = naslov;
        this.brojStrani = broj_strani;
        this.nastan = nastan_id;
    }
}