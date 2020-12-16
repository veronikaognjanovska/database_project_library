package library_project.lab.service.impl;

import library_project.lab.model.DateCustom;
import library_project.lab.model.Kniga;
import library_project.lab.model.Primerok;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.KnigaRepository;
import library_project.lab.repository.PrimerokRepository;
import library_project.lab.service.KnigaService;
import library_project.lab.service.PrimerokService;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZonedDateTime;
import java.util.Optional;

public class PrimerokServiceImpl implements PrimerokService {

    private final PrimerokRepository primerokRepository;
    private final KnigaService knigaService;

    public PrimerokServiceImpl(PrimerokRepository primerokRepository, KnigaService knigaService) {
        this.primerokRepository = primerokRepository;
        this.knigaService = knigaService;
    }

    @Override
    public Primerok save(Long seriskiBroj,  STATUS_PRIMEROK status)
            throws IllegalArgumentException, AlreadyExistsException, NotFound {
        if (seriskiBroj==null || status==null) {
            throw new IllegalArgumentException();
        }
        Kniga kniga=knigaService.findById(seriskiBroj); //  there must be a book first - just as a check
        Primerok primerok = new Primerok(seriskiBroj,status);// inventaren_broj is generated
        primerokRepository.save(primerok);
        return primerok;
    }

    @Override
    public Primerok findBySeriskiBrojAndInventarenBroj(Long seriskiBroj, Long inventarenBroj) throws NotFound {
        return null;
    }

    @Override
    public void update(STATUS_PRIMEROK status, ZonedDateTime date, Long seriskiBroj, Long inventarenBroj) {
        this.primerokRepository.update(status, DateCustom.getDateString(date),seriskiBroj,inventarenBroj);
    }
}
