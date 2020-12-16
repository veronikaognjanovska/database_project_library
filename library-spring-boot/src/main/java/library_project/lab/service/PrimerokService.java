package library_project.lab.service;

import library_project.lab.model.Kniga;
import library_project.lab.model.Primerok;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZonedDateTime;
import java.util.Date;

public interface PrimerokService {
    Primerok save(Long seriskiBroj, STATUS_PRIMEROK status) throws NotFound;
    Primerok findBySeriskiBrojAndInventarenBroj(Long seriskiBroj,Long inventarenBroj) throws NotFound;
    void update(STATUS_PRIMEROK status, ZonedDateTime date, Long seriskiBroj, Long inventarenBroj);
}
