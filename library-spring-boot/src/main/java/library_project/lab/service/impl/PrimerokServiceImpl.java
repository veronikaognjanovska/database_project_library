package library_project.lab.service.impl;

import library_project.lab.model.*;
import library_project.lab.model.enumeration.STATUS_PRIMEROK;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.model.keys.PrimerokKey;
import library_project.lab.repository.PrimerokRepository;
import library_project.lab.service.KnigaService;
import library_project.lab.service.PrimerokService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class PrimerokServiceImpl implements PrimerokService {

    private final PrimerokRepository primerokRepository;
    private final KnigaService knigaService;
   private final EntityManager entityManager;


    public PrimerokServiceImpl(PrimerokRepository primerokRepository,
                               KnigaService knigaService, EntityManager entityManager) {
        this.primerokRepository = primerokRepository;
        this.knigaService = knigaService;

        this.entityManager = entityManager;
    }

    @Override
    public Primerok save(Long seriskiBroj,  STATUS_PRIMEROK status)
            throws IllegalArgumentException, AlreadyExistsException, NotFound {
        if (seriskiBroj==null || status==null) {
            throw new IllegalArgumentException();
        }
        Kniga kniga=knigaService.findById(seriskiBroj); //  there must be a book first - just as a check
//        PrimerokKey key = new PrimerokKey(seriskiBroj,
//                SequenceInventarenBroj.getInstance().getNextSequence());
        Query q = entityManager.createNativeQuery("SELECT nextval('sequence_inventaren_broj');");
        BigInteger result= (BigInteger)q.getSingleResult();
        Long i= result.longValue();
        PrimerokKey key = new PrimerokKey(seriskiBroj,i);
        Primerok primerok = new Primerok(key,status);
        primerokRepository.save(primerok);
        return primerok;
    }

    @Override
    public Primerok findBySeriskiBrojAndInventarenBroj(Long seriskiBroj, Long inventarenBroj) throws NotFound {
        Optional<Primerok> primerok =  primerokRepository.findBySeriskiBrojAndInventarenBroj(seriskiBroj, inventarenBroj);
        if(!primerok.isPresent()){
            throw new NotFound();
        }
        return primerok.get();
    }

    @Override
    @Transactional
    public void update(STATUS_PRIMEROK status, Long seriskiBroj, Long inventarenBroj) {
        this.primerokRepository.update(status.toString(), seriskiBroj,inventarenBroj);
    }
}
