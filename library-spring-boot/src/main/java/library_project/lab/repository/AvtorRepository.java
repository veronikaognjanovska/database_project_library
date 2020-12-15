package library_project.lab.repository;

import library_project.lab.model.Avtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvtorRepository extends JpaRepository<Avtor, Long> {
    Optional<Avtor> findByImeAndAndPrezimeAndGodina(String ime, String prezime, Integer godina_na_ragjanje);
}