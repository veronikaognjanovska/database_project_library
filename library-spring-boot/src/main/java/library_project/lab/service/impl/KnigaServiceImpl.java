package library_project.lab.service.impl;


import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.KnigaRepository;
import library_project.lab.service.KnigaService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KnigaServiceImpl implements KnigaService {

    private final KnigaRepository knigaRepository;

    public KnigaServiceImpl(KnigaRepository knigaRepository) {
        this.knigaRepository = knigaRepository;
    }

    @Override
    public Kniga save(String naslov, Integer broj_strani, Nastan nastan_id) {

        if (naslov==null || naslov.isEmpty() || broj_strani==null || nastan_id==null) {
            throw new IllegalArgumentException();
        }
        if(knigaRepository.findByNaslovAndBrojStraniAndNastan(naslov, broj_strani, nastan_id).isPresent()){
            throw new AlreadyExistsException();
        }
        Kniga c = new Kniga(naslov, broj_strani, nastan_id);
        knigaRepository.save(c);
        return c;
    }
    @Override
    public Kniga findById(Long id) throws NotFound {
        Optional<Kniga> kniga =  knigaRepository.findById(id);
        if(!kniga.isPresent()){
            throw new NotFound();
        }
        return kniga.get();
    }
}
