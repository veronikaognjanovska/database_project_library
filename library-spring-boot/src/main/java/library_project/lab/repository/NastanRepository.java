package library_project.lab.repository;

import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface NastanRepository extends JpaRepository<Nastan, Long> {
    Optional<Nastan> findByDatumAndEmbgVrabotenGlaven(Date date, Vraboten embg_vraboten_glaven);
}
