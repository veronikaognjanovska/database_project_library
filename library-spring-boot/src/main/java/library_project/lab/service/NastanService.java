package library_project.lab.service;

import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.util.Date;

public interface NastanService {
    Nastan save(Date date, Vraboten embg_vraboten_glaven);
    Nastan findById(Long id) throws NotFound;
}
