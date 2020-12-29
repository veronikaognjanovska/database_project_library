package library_project.lab.model.keys;

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


    private Long seriski_broj;

    private Long inventaren_broj;

    public PrimerokKey() { }

    public PrimerokKey(Long seriski_broj, Long inventaren_broj) {
        this.seriski_broj = seriski_broj;
        this.inventaren_broj = inventaren_broj;
    }
}
