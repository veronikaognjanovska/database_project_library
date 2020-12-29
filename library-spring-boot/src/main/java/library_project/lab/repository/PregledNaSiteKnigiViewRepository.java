package library_project.lab.repository;

import library_project.lab.model.views.PregledNaSiteKnigiView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PregledNaSiteKnigiViewRepository extends JpaRepository<PregledNaSiteKnigiView, Long> {

    @Query(value="SELECT * FROM pregled_na_site_knigi",
            nativeQuery = true)
    List<PregledNaSiteKnigiView> selectAll();


}
