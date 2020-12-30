package library_project.lab.service.impl;


import library_project.lab.model.Avtor;
import library_project.lab.model.Kniga;
import library_project.lab.model.Nastan;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.KnigaRepository;
import library_project.lab.service.AvtorService;
import library_project.lab.service.KnigaService;
import library_project.lab.service.NastanService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class KnigaServiceImpl implements KnigaService {

    private final KnigaRepository knigaRepository;
    private final AvtorService avtorService;
    private final NastanService nastanService;

    public KnigaServiceImpl(KnigaRepository knigaRepository, AvtorService avtorService, NastanService nastanService, EntityManager entityManager) {
        this.knigaRepository = knigaRepository;
        this.avtorService = avtorService;
        this.nastanService = nastanService;
        this.entityManager = entityManager;
    }

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public Kniga save(String naslov, Integer broj_strani, Long nastan_id)
            throws IllegalArgumentException, AlreadyExistsException, NotFound {

        if (naslov==null || naslov.isEmpty() ) {
            throw new IllegalArgumentException();
        }
        Nastan nastan=nastanService.findById(nastan_id);
        if(knigaRepository.findByNaslovAndBrojStraniAndNastan(naslov, broj_strani, nastan).isPresent()){
            throw new AlreadyExistsException();
        }
        Kniga c = new Kniga(naslov, broj_strani, nastan);
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

    @Override
    @Transactional
    public Kniga addAvtorToKniga(Long avtorid , Long knigaid) throws NotFound {
        Kniga kniga = this.findById(knigaid);
        Avtor avtor = this.avtorService.findById(avtorid);
        kniga.getAvtoriSet().add(avtor);
        return this.knigaRepository.save(kniga);
    }

    @Override
    @Transactional
    public void updateBrojStrani(Integer brojStrani, Long seriskiBroj)
            throws IllegalArgumentException, NotFound {
        if (brojStrani==null || seriskiBroj==null ) {
            throw new IllegalArgumentException();
        }
        if( !knigaRepository.findById(seriskiBroj).isPresent()){
            throw new NotFound();
        }
        knigaRepository.updateBrojStrani(brojStrani,seriskiBroj);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updateNastanId(Long nastanId, Long seriskiBroj)
            throws IllegalArgumentException, NotFound {
        if (nastanId==null || seriskiBroj==null ) {
            throw new IllegalArgumentException();
        }
        if( !knigaRepository.findById(seriskiBroj).isPresent()){
            throw new NotFound();
        }
        knigaRepository.updateNastanId(nastanId,seriskiBroj);
        entityManager.flush();
    }

    @Override
    public List<Kniga> findAll() {
        return knigaRepository.findAll();
    }

}
