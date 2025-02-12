package library_project.lab.service;

import library_project.lab.model.Avtor;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.List;

public interface AvtorService {
    Avtor save(String ime, String prezime, Integer godina_na_ragjanje);
    Avtor findById(Long id) throws NotFound;

    List<Avtor> findAll();
}
