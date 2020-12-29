package library_project.lab.repository;

import library_project.lab.model.views.DostapnostNaKnigiView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DostapnostNaKnigiViewRepository extends JpaRepository<DostapnostNaKnigiView, Long> {

    @Query(value="SELECT * FROM dostapnost_na_knigi",
            nativeQuery = true)
    List<DostapnostNaKnigiView> selectAll();


}
