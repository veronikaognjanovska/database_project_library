package library_project.lab.model.forms;


import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Immutable
@SqlResultSetMapping(
        name = "NajchestoIznajmuvaniKnigiPoMesecFormMapping",
        classes = @ConstructorResult(
                targetClass = NajchestoIznajmuvaniKnigiPoMesecForm.class,
                columns = {
                        @ColumnResult(name = "mesec",type = Integer.class),
                        @ColumnResult(name = "seriski_broj",type = Long.class),
                        @ColumnResult(name = "naslov"),
                        @ColumnResult(name = "broj_strani",type = Integer.class),
                        @ColumnResult(name = "avtor"),
                        @ColumnResult(name = "objavena_na_nastan_na_datum", type=String.class)
                }))
public class NajchestoIznajmuvaniKnigiPoMesecForm implements Serializable {

    @Id
    @Column(name = "mesec")
    private Integer mesec;

    @Column(name = "seriski_broj")
    private Long seriskiBroj;

    private String naslov;

    @Column(name = "broj_strani")
    private Integer brojStrani;

    private String avtor;

    @Column(name = "objavena_na_nastan_na_datum")
    private String objavenaNaNastanNaDatum;



    public NajchestoIznajmuvaniKnigiPoMesecForm() {}


}