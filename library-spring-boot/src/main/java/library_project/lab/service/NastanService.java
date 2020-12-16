package library_project.lab.service;

import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZonedDateTime;
import java.util.Date;

public interface NastanService {
    Nastan save(ZonedDateTime date, String embg_vraboten_glaven) throws NotFound;
    Nastan findById(Long id) throws NotFound;
}
