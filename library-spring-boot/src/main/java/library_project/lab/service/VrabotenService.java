package library_project.lab.service;

import library_project.lab.model.Vraboten;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Date;


public interface VrabotenService {
    Vraboten save(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, Date datum_na_vrabotuvanje);
    Vraboten findByEmbg(String embg) throws NotFound;
}
