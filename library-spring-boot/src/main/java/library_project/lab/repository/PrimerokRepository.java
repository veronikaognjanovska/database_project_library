package library_project.lab.repository;

import library_project.lab.model.Primerok;
import library_project.lab.model.keys.PrimerokKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrimerokRepository  extends JpaRepository<Primerok, PrimerokKey> {

    @Query(value="SELECT * FROM primerok b WHERE b.seriski_broj=?1 and b.inventaren_broj = ?2 ",
            nativeQuery = true)
    Optional<Primerok> findBySeriskiBrojAndInventarenBroj(Long seriskiBroj, Long inventarenBroj);

    @Modifying(flushAutomatically = true)
    @Query(value = "update primerok p set status = ?1 where p.seriski_broj = ?2 and p.inventaren_broj = ?3 ",
            nativeQuery = true)
    void update(String status, Long seriskiBroj, Long inventarenBroj);

}
