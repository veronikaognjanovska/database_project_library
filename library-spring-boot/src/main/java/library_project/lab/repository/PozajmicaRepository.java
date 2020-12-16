package library_project.lab.repository;


import library_project.lab.model.Pozajmica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PozajmicaRepository  extends JpaRepository<Pozajmica, Long> {
}
