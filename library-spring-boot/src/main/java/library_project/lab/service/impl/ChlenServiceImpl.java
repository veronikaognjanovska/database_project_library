package library_project.lab.service.impl;

import library_project.lab.model.Chlen;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.ChlenRepository;
import library_project.lab.service.ChlenService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ChlenServiceImpl implements ChlenService {

    private final ChlenRepository chlenRepository;

    public ChlenServiceImpl(ChlenRepository chlenRepository) {
        this.chlenRepository = chlenRepository;
    }


    @Override
    public Chlen save(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, Date datum_na_zachlenuvanje)
            throws IllegalArgumentException,AlreadyExistsException{
        if (embg==null || embg.isEmpty() || ime==null || ime.isEmpty() || prezime==null || prezime.isEmpty() ||
                datum_na_ragjanje==null  || adresa_na_ziveenje==null || adresa_na_ziveenje.isEmpty() ||
                telefonski_broj==null || telefonski_broj.isEmpty() || datum_na_zachlenuvanje==null) {
            throw new IllegalArgumentException();
        }
        if(chlenRepository.existsById(embg)){
            throw new AlreadyExistsException();
        }
        Chlen c = new Chlen(embg, ime, prezime, datum_na_ragjanje, adresa_na_ziveenje, telefonski_broj, datum_na_zachlenuvanje);
        chlenRepository.save(c);
        return c;
    }

    @Override
    public Chlen findByEmbg(String embg) throws NotFound {
        Optional<Chlen> chlen =  chlenRepository.findById(embg);
        if(!chlen.isPresent()){
            throw new NotFound();
        }
        return chlen.get();
    }

}
