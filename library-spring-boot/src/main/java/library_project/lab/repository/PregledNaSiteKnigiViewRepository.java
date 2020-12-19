package library_project.lab.repository;

import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import library_project.lab.model.PregledNaSiteKnigiView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PregledNaSiteKnigiViewRepository extends JpaRepository<PregledNaSiteKnigiView, Long> {

    @Query(value="SELECT * FROM pregled_na_site_knigi",
            nativeQuery = true)
    List<PregledNaSiteKnigiView> selectAll();


}
