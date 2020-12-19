package library_project.lab.service;

import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface KnigaService {
    Kniga save (String naslov, Integer broj_strani, Nastan nastan_id);
    Kniga findById(Long id) throws NotFound;
    Kniga addAvtorToKniga(Long avtorid , Long knigaid) throws NotFound;

    void updateBrojStrani(Integer brojStrani, Long seriskiBroj) throws NotFound;
    void updateNastanId(Long nastanId, Long seriskiBroj) throws NotFound;
}
