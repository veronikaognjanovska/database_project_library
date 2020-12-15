package library_project.lab.service.impl;


import library_project.lab.model.Vraboten;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.VrabotenRepository;
import library_project.lab.service.VrabotenService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VrabotenServiceImpl implements VrabotenService {
    private final VrabotenRepository vrabotenRepository;

    public VrabotenServiceImpl(VrabotenRepository vrabotenRepository) {
        this.vrabotenRepository = vrabotenRepository;
    }


    @Override
    public Vraboten save(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj, Date datum_na_vrabotuvanje) {
        if (embg==null || embg.isEmpty() || ime==null || ime.isEmpty() || prezime==null || prezime.isEmpty() ||
                datum_na_ragjanje==null  || adresa_na_ziveenje==null || adresa_na_ziveenje.isEmpty() ||
                telefonski_broj==null || telefonski_broj.isEmpty() || datum_na_vrabotuvanje==null) {
            throw new IllegalArgumentException();
        }
        if(vrabotenRepository.existsById(embg)){
            throw new AlreadyExistsException();
        }
        Vraboten v = new Vraboten(embg, ime, prezime, datum_na_ragjanje, adresa_na_ziveenje, telefonski_broj, datum_na_vrabotuvanje);
        vrabotenRepository.save(v);
        return v;
    }

    @Override
    public Vraboten findByEmbg(String embg) throws NotFound {
        Optional<Vraboten> vraboten =  vrabotenRepository.findById(embg);
        if(!vraboten.isPresent()){
            throw new NotFound();
        }
        return vraboten.get();
    }

}
