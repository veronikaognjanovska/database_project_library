package library_project.lab.service;


import library_project.lab.model.Pozajmica;
import org.omg.CosNaming.NamingContextPackage.NotFound;


import java.time.ZonedDateTime;
import java.util.Date;

public interface PozajmicaService {
    Pozajmica save(Long seriskiBroj, Long inventarenBroj,String embgChlen,String embgVraboten) throws NotFound;
    void updateToClosed(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, ZonedDateTime date);
    Pozajmica findBy(Long seriskiBroj, Long inventarenBroj,String embgChlen,String embgVraboten,ZonedDateTime date) throws NotFound;
}
