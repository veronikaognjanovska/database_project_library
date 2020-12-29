package library_project.lab.repository;

import library_project.lab.model.MomentalniPozajmiciView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MomentalniPozajmiciViewRepository extends JpaRepository<MomentalniPozajmiciView, Long> {

    @Query(value="SELECT * FROM momentalni_pozajmici",
            nativeQuery = true)
    List<MomentalniPozajmiciView> selectAll();


}
