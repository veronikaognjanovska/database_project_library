package library_project.lab.service.impl;

import library_project.lab.model.Avtor;
import library_project.lab.model.Nastan;
import library_project.lab.model.Vraboten;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.NastanRepository;
import library_project.lab.service.NastanService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class NastanServiceImpl implements NastanService {

    private final NastanRepository nastanRepository;

    public NastanServiceImpl(NastanRepository nastanRepository) {
        this.nastanRepository = nastanRepository;
    }

    @Override
    public Nastan save(Date date, Vraboten embg_vraboten_glaven)
            throws IllegalArgumentException,AlreadyExistsException{
        if (date==null || embg_vraboten_glaven==null) {
            throw new IllegalArgumentException();
        }
        if(nastanRepository.findByDatumAndEmbgVrabotenGlaven(date, embg_vraboten_glaven).isPresent()){
            throw new AlreadyExistsException();
        }
        Nastan c = new Nastan(date, embg_vraboten_glaven);
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
