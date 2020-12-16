package library_project.lab.service.impl;

import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.NastanRepository;
import library_project.lab.service.NastanService;
import library_project.lab.service.VrabotenService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class NastanServiceImpl implements NastanService {

    private final NastanRepository nastanRepository;
    private final VrabotenService vrabotenService;

    public NastanServiceImpl(NastanRepository nastanRepository, VrabotenService vrabotenService) {
        this.nastanRepository = nastanRepository;
        this.vrabotenService = vrabotenService;
    }

    @Override
    public Nastan save(ZonedDateTime date, String embg_vraboten_glaven)
            throws IllegalArgumentException, AlreadyExistsException, NotFound {
        Vraboten vraboten = vrabotenService.findByEmbg(embg_vraboten_glaven);
        if (date==null || embg_vraboten_glaven==null) {
            throw new IllegalArgumentException();
        }
        if(nastanRepository.findByDatumAndEmbgVrabotenGlaven(date, vraboten).isPresent()){
            throw new AlreadyExistsException();
        }
        Nastan c = new Nastan(date, vraboten);
        nastanRepository.save(c);
        return c;
    }

    @Override
    public Nastan findById(Long id) throws NotFound {
        Optional<Nastan> nastan =  nastanRepository.findById(id);
        if(!nastan.isPresent()){
            throw new NotFound();
        }
        return nastan.get();
    }


}
