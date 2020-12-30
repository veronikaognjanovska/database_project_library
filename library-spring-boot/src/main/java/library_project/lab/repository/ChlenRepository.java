package library_project.lab.repository;

import library_project.lab.model.Avtor;
import library_project.lab.model.Chlen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChlenRepository extends JpaRepository<Chlen, String> {

}

