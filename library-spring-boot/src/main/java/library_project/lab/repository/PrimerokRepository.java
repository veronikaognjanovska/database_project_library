package library_project.lab.repository;

import library_project.lab.model.DateCustom;
import library_project.lab.model.Primerok;
import library_project.lab.model.PrimerokKey;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface PrimerokRepository  extends JpaRepository<Primerok, PrimerokKey> {

    @Query(value="SELECT * FROM primerok b WHERE b.seriski_broj=?1 and b.inventaren_broj = ?2 ",
            nativeQuery = true)
    Optional<Primerok> findBySeriskiBrojAndInventarenBroj(Long seriskiBroj, Long inventarenBroj);

    @Modifying(flushAutomatically = true)
    @Query(value = "update primerok set status=?1, datum_vrakjanje=?2 where seriski_broj=?3 and inventaren_broj=?4;",
            nativeQuery = true)
    void update(STATUS_PRIMEROK status,String date, Long seriskiBroj, Long inventarenBroj);

}
