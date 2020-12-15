package library_project.lab.repository;


import library_project.lab.model.Vraboten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VrabotenRepository extends JpaRepository<Vraboten, String> {

}
