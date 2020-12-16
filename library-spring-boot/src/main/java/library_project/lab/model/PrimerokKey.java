package library_project.lab.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Embeddable
@Data
public class PrimerokKey implements Serializable {

    @NotNull
    private Long seriski_broj;
    @NotNull
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long inventaren_broj;

    public PrimerokKey() { }

    public PrimerokKey(Long seriski_broj) {
        this.seriski_broj = seriski_broj;
    }
}
