package library_project.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;


@Data
@Entity
public class Nastan implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long nastan_id;

    @Column(nullable = false)
    private ZonedDateTime datum;

    @ManyToOne
    @JoinColumn(name="embg_vraboten_glaven")
    private Vraboten embgVrabotenGlaven;

    public Long getNastanId(){return nastan_id;}

    public Nastan() {}

    public Nastan(ZonedDateTime date, Vraboten embg_vraboten_glaven) {
        this.datum = date;
        this.embgVrabotenGlaven = embg_vraboten_glaven;
    }


}