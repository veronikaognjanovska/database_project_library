package library_project.lab.repository;

import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KnigaRepository extends JpaRepository<Kniga, Long> {
    Optional<Kniga> findByNaslovAndBrojStraniAndNastan(String naslov, Integer broj_strani, Nastan nastan_id);

}
