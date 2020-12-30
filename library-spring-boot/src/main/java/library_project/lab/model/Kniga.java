package library_project.lab.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="seriski_broj")

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "kniga_napishana_avtor",
            joinColumns = {@JoinColumn(name = "seriski_broj")},
            inverseJoinColumns = {@JoinColumn(name = "avtor_id")}
    )
    private Set<Avtor> avtoriSet=new HashSet<>();

    public Long getSeriskiBroj(){return seriski_broj;}
    public String getNaslov(){return naslov;}
    public Nastan getNastan(){return nastan;}
    public Set<Avtor> getAvtoriSet(){return avtoriSet;}


    public Kniga() {}

    public Kniga(String naslov, Integer broj_strani, Nastan nastan_id) {
        this.naslov = naslov;
        this.brojStrani = broj_strani;
        this.nastan = nastan_id;
        this.avtoriSet= new HashSet<>();
    }
}