package library_project.lab.repository;

import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KnigaRepository extends JpaRepository<Kniga, Long> {
    Optional<Kniga> findByNaslovAndBrojStraniAndNastan(String naslov, Integer broj_strani, Nastan nastan_id);

    @Modifying(flushAutomatically = true)
    @Query(value = "update kniga set broj_strani = ?1 where seriski_broj = ?2 ",
            nativeQuery = true)
    void updateBrojStrani(Integer brojStrani, Long seriskiBroj);

    @Modifying(flushAutomatically = true)
    @Query(value = "update kniga set nastan_id = ?1 where seriski_broj = ?2 ",
            nativeQuery = true)
    void updateNastanId(Long nastanId, Long seriskiBroj);
}
