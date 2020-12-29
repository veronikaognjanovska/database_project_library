package library_project.lab.repository;

import library_project.lab.model.forms.NajchestoIznajmuvaniKnigiPoMesecForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NajchestoIznajmuvaniKnigiPoMesecFormRepository extends JpaRepository<NajchestoIznajmuvaniKnigiPoMesecForm, Long> {

    @Query(value="select najbarani_knigi_count.mesec, najbarani_knigi_count.seriski_broj," +
            "        k.naslov,k.broj_strani,k.avtor,k.objavena_na_nastan_na_datum" +
            "    from (with fullCount(mesec,seriski_broj,count) as" +
            "            (" +
            "                select extract(month from sp.datum_pozajmuvanje) as mesec,sp.seriski_broj, count(*) as count" +
            "                from site_pozajmici as sp" +
            "                group by sp.seriski_broj, extract(month from sp.datum_pozajmuvanje)" +
            "            )," +
            "            maxed (mesec,maxCount) as" +
            "            (" +
            "                select mesec,max(count)" +
            "                from fullCount" +
            "                group by mesec" +
            "            )" +
            "            select f.mesec,f.seriski_broj" +
            "            from fullCount f,maxed m" +
            "            where (f.mesec,f.count) = (m.mesec,m.maxCount)" +
            "            )" +
            "        as najbarani_knigi_count" +
            "    left join pregled_na_site_knigi k on k.seriski_broj= najbarani_knigi_count.seriski_broj;",
            nativeQuery = true)
    List<NajchestoIznajmuvaniKnigiPoMesecForm> selectAll();

}