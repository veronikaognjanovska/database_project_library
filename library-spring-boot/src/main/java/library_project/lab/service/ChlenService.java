package library_project.lab.service;

import library_project.lab.model.Chlen;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface ChlenService {
    Chlen save(String embg, String ime, String prezime, ZonedDateTime datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, ZonedDateTime datum_na_zachlenuvanje);
    Chlen findByEmbg(String embg) throws NotFound;
    List<Chlen> findAll();
}
