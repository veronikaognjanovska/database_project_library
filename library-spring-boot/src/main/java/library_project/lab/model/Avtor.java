package library_project.lab.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Avtor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long avtor_id;

    @Column(nullable = false)
    private String ime;
    @Column(nullable = false)
    private String prezime;
    @Column(nullable = false,name = "godina_na_ragjanje")
    private Integer godina;

    @ManyToMany(mappedBy = "avtoriSet", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Kniga> knigiSet;


    public Avtor() {}
    public Avtor(String ime, String prezime, Integer godina_na_ragjanje) {
        this.ime = ime;
        this.prezime = prezime;
        this.godina = godina_na_ragjanje;
        this.knigiSet= new HashSet<>();
    }
}
