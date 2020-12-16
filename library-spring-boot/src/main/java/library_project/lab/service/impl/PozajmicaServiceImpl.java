package library_project.lab.service.impl;

import library_project.lab.model.*;
import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.PozajmicaRepository;
import library_project.lab.service.ChlenService;
import library_project.lab.service.PozajmicaService;
import library_project.lab.service.PrimerokService;
import library_project.lab.service.VrabotenService;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class PozajmicaServiceImpl implements PozajmicaService {

    private final PozajmicaRepository pozajmicaRepository;
    private final PrimerokService primerokService;
    private final ChlenService chlenService;
    private final VrabotenService vrabotenService;

    public PozajmicaServiceImpl(PozajmicaRepository pozajmicaRepository, PrimerokService primerokService, ChlenService chlenService, VrabotenService vrabotenService) {
        this.pozajmicaRepository = pozajmicaRepository;
        this.primerokService = primerokService;
        this.chlenService = chlenService;
        this.vrabotenService = vrabotenService;
    }


    @Override
    public Pozajmica save(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten) throws IllegalArgumentException, AlreadyExistsException, NotFound {

        if (seriskiBroj==null || inventarenBroj==null ||
                embgChlen==null || embgChlen.isEmpty() || embgVraboten==null || embgVraboten.isEmpty()  ) {
            throw new IllegalArgumentException();
        }
        Primerok primerok=primerokService.findBySeriskiBrojAndInventarenBroj(seriskiBroj, inventarenBroj);
        Chlen chlen=chlenService.findByEmbg(embgChlen);
        Vraboten vraboten=vrabotenService.findByEmbg(embgVraboten);
        Pozajmica pozajmica= new Pozajmica(seriskiBroj,inventarenBroj,chlen,vraboten, DateCustom.getDateNow(), STATUS_POZAJMICA.ACTIVE);
        pozajmicaRepository.save(pozajmica);
        return pozajmica;
    }

    @Override
    public void update(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, ZonedDateTime date) {
        this.pozajmicaRepository.update(seriskiBroj,inventarenBroj,embgChlen,embgVraboten,DateCustom.getDateString(date));
    }

    @Override
    public Pozajmica findBy(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, Date date) throws NotFound {
        Optional<Pozajmica> pozajmica = pozajmicaRepository.findBy(seriskiBroj, inventarenBroj, embgChlen, embgVraboten, date);
        if(!pozajmica.isPresent()){
            throw new NotFound();
        }
        return pozajmica.get();
    }
}