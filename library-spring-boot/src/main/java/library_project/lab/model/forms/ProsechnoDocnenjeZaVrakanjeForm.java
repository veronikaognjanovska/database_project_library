package library_project.lab.model.forms;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Immutable
@SqlResultSetMapping(
        name = "ProsechnoDocnenjeZaVrakanjeFormMapping",
        classes = @ConstructorResult(
                targetClass = ProsechnoDocnenjeZaVrakanjeForm.class,
                columns = {
                        @ColumnResult(name = "embg",type = String.class),
                        @ColumnResult(name = "chlen"),
                        @ColumnResult(name = "chlen_telefonski_broj"),
                        @ColumnResult(name = "prosechno_docnenje")
                }))
public class ProsechnoDocnenjeZaVrakanjeForm implements Serializable {

    @Id
    @Column(length = 13)
    private String embg;

    private String chlen;

    @Column(name = "chlen_telefonski_broj")
    private String chlenTelefonskiBroj;

    @Column(name = "prosechno_docnenje")
    private String prosechnoDocnenje;



    public ProsechnoDocnenjeZaVrakanjeForm() {}


}