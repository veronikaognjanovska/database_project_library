package library_project.lab.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Immutable
@SqlResultSetMapping(
        name = "BookCountMapping",
        classes = @ConstructorResult(
                targetClass = DostapnostNaKnigiView.class,
                columns = {
                        @ColumnResult(name = "seriski_broj", type = Long.class),
                        @ColumnResult(name = "VKUPNO", type = Integer.class),
                        @ColumnResult(name = "AVAILABLE", type = Integer.class)
                }))
public class DostapnostNaKnigiView implements Serializable {

    @Id
    @Column(name = "seriski_broj")
    private Long seriskiBroj;

    @Column(name = "VKUPNO")
    private Integer VKUPNO;

    @Column(name = "AVAILABLE")
    private Integer AVAILABLE;


    public DostapnostNaKnigiView() {}


}