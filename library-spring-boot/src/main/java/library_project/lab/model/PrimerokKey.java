package library_project.lab.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


public class PrimerokKey implements Serializable {

    private Long seriski_broj;
    private Long inventaren_broj;

}
