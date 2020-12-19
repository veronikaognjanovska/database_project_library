package library_project.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

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
                        @ColumnResult(name = "authorName"),
                        @ColumnResult(name = "objavena_na_nastan_na_datum", type=ZonedDateTime.class)
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
    private ZonedDateTime objavenaDatum;

    public PregledNaSiteKnigiView() {}


}
