package library_project.lab.service.impl;

import library_project.lab.model.*;
import library_project.lab.model.enumeration.STATUS_POZAJMICA;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.model.helpers.DateCustom;
import library_project.lab.repository.PozajmicaRepository;
import library_project.lab.service.ChlenService;
import library_project.lab.service.PozajmicaService;
import library_project.lab.service.PrimerokService;
import library_project.lab.service.VrabotenService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
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
    @Transactional
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
        this.primerokService.update(STATUS_PRIMEROK.UNAVAILABLE,seriskiBroj,inventarenBroj);
        return pozajmica;
    }

    @Override
    @Transactional
    public void updateToClosed(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, ZonedDateTime date) {
        String dateString=DateCustom.getDateString(date);
        this.pozajmicaRepository.update(seriskiBroj,inventarenBroj,embgChlen,embgVraboten,dateString);
        this.primerokService.update(STATUS_PRIMEROK.AVAILABLE,seriskiBroj,inventarenBroj);
    }

    @Override
    public Pozajmica findBy(Long seriskiBroj, Long inventarenBroj, String embgChlen, String embgVraboten, ZonedDateTime date) throws NotFound {
        String dateString=DateCustom.getDateString(date);
        Optional<Pozajmica> pozajmica = pozajmicaRepository.findBy(seriskiBroj, inventarenBroj, embgChlen, embgVraboten, dateString);
        if(!pozajmica.isPresent()){
            throw new NotFound();
        }
        return pozajmica.get();
    }
}
