package library_project.lab.model.views;

import library_project.lab.model.keys.MomentalniPozajmiciKey;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Immutable
@IdClass(MomentalniPozajmiciKey.class)
@SqlResultSetMapping(
        name = "MomentalniPozajmiciMapping",
        classes = @ConstructorResult(
                targetClass = MomentalniPozajmiciView.class,
                columns = {
                        @ColumnResult(name = "chlen"),
                        @ColumnResult(name = "chlen_telefonski_broj"),
                        @ColumnResult(name = "datum_na_zachlenuvanje", type=String.class),
                        @ColumnResult(name = "seriski_broj", type = Long.class),
                        @ColumnResult(name = "inventaren_broj", type = Long.class),
                        @ColumnResult(name = "naslov"),
                        @ColumnResult(name = "avtor"),
                        @ColumnResult(name = "datum_pozajmuvanje", type=String.class),
                        @ColumnResult(name = "vraboten"),
                        @ColumnResult(name = "vraboten_telefonski_broj"),
                        @ColumnResult(name = "datum_na_vrabotuvanje", type= String.class)
                }))
public class MomentalniPozajmiciView implements Serializable {

    @Column(name = "chlen")
    private String chlen;

    @Column(name = "chlen_telefonski_broj")
    private String chlenTelefonskiBroj;

    @Column(name = "datum_na_zachlenuvanje")
    private String datumNaZachlenuvanje;

    @Id
    @Column(name = "seriski_broj")
    private Long seriskiBroj;

    @Id
    @Column(name = "inventaren_broj")
    private Long inventarenBroj;

    private String naslov;
    private String avtor;

    @Id
    @Column(name = "datum_pozajmuvanje")
    private String datumPozajmuvanje;

    @Column(name = "vraboten")
    private String vraboten;

    @Column(name = "vraboten_telefonski_broj")
    private String vrabotenTelefonskiBroj;

    @Column(name = "datum_na_vrabotuvanje")
    private String datumNaVrabotuvanje;


    public MomentalniPozajmiciView() {}


}