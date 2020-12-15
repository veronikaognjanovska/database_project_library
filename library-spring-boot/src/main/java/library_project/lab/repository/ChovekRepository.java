package library_project.lab.repository;

import library_project.lab.model.Chovek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChovekRepository extends JpaRepository<Chovek, String> {

}
