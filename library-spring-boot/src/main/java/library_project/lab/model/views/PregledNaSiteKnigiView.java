package library_project.lab.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Immutable
//@NamedNativeQuery(name = "findUniqueCameronsInOrder",
//        query = "SELECT * FROM pregled_na_site_knigi",
//        resultClass = Cameron.class)
@SqlResultSetMapping(
        name = "BookValueMapping",
        classes = @ConstructorResult(
                targetClass = PregledNaSiteKnigiView.class,
                columns = {
                        @ColumnResult(name = "seriski_broj", type = Long.class),
                        @ColumnResult(name = "naslov"),
                        @ColumnResult(name = "broj_strani", type = Long.class),
                        @ColumnResult(name = "avtor"),
                        @ColumnResult(name = "objavena_na_nastan_na_datum", type=String.class)
                }))
public class PregledNaSiteKnigiView implements Serializable {

    @Id
    @Column(name = "seriski_broj")
    private Long seriskiBroj;

    private String naslov;

    @Column(name = "broj_strani")
    private Integer brojStrani;

    private String avtor;

    @Column(name = "objavena_na_nastan_na_datum")
    private String objavenaDatum;

    public PregledNaSiteKnigiView() {}


}
