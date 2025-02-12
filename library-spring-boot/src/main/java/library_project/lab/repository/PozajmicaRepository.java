package library_project.lab.repository;


import library_project.lab.model.Pozajmica;
import library_project.lab.model.keys.PozajmicaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PozajmicaRepository  extends JpaRepository<Pozajmica, PozajmicaKey> {
    @Query(value="SELECT * FROM pozajmica b WHERE b.seriski_broj = ?1 and b.inventaren_broj = ?2 and b.chlen_embg = ?3 and b.vraboten_embg = ?4 and b.datum_pozajmuvanje = ?5\\:\\:date ",
            nativeQuery = true)
    Optional<Pozajmica> findBy(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, String date);

    @Modifying(flushAutomatically = true)
    @Query(value = "update pozajmica set status='CLOSED', datum_vrakjanje=now()\\:\\:date where seriski_broj= ?1 and inventaren_broj= ?2 and chlen_embg= ?3 and vraboten_embg= ?4 and datum_pozajmuvanje= ?5\\:\\:date and status='ACTIVE'",
            nativeQuery = true)
//    void update(Long seriskiBroj);
    void update(Long seriskiBroj, Long inventarenBroj,String embgChlen,String embgVraboten, String dateStr);
}

