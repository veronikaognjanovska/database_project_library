package library_project.lab.repository;

import library_project.lab.model.forms.ProsechnoDocnenjeZaVrakanjeForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProsechnoDocnenjeZaVrakanjeFormRepository extends JpaRepository<ProsechnoDocnenjeZaVrakanjeForm, Long> {

    @Query(value="with docniCount(embg,docniAvg) as" +
            "    (" +
            "        select p.chlen_embg, abs(avg(p.datum_pozajmuvanje-(case when p.datum_vrakjanje is not null then p.datum_vrakjanje else now()\\:\\:date end))) as docni_za" +
            "        from pozajmica p" +
            "        group by p.chlen_embg" +
            "    )" +
            "select c.embg, c2.ime || ' ' || c2.prezime as chlen," +
            "       c2.telefonski_broj as chlen_telefonski_broj," +
            "       extract(day from (d.docniAvg * interval '1 day')) || ' days' as prosechno_docnenje" +
            "    from chlen c" +
            "    left join chovek c2 on c.embg = c2.embg" +
            "    left join docniCount d on c.embg = d.embg;",
            nativeQuery = true)
    List<ProsechnoDocnenjeZaVrakanjeForm> selectAll();

}