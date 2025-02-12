package library_project.lab.service;

import library_project.lab.model.Chovek;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZonedDateTime;
import java.util.List;

public interface ChovekService {

    Chovek save(String embg, String ime, String prezime, ZonedDateTime datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj);
    Chovek findByEmbg(String embg) throws NotFound;
    List<Chovek> findAll();
}
