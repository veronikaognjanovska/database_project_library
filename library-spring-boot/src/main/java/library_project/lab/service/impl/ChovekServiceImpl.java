package library_project.lab.service.impl;


import library_project.lab.model.Chovek;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.ChovekRepository;
import library_project.lab.service.ChovekService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.Optional;

@Service
public class ChovekServiceImpl implements ChovekService {

    private final ChovekRepository chovekRepository;

    public ChovekServiceImpl(ChovekRepository chovekRepository) {
        this.chovekRepository = chovekRepository;
    }


    @Override
    public Chovek save(String embg, String ime, String prezime, Date datum_na_ragjanje, String adresa_na_ziveenje, String telefonski_broj) {
        if (embg==null || embg.isEmpty() || ime==null || ime.isEmpty() || prezime==null || prezime.isEmpty() ||
                datum_na_ragjanje==null  || adresa_na_ziveenje==null || adresa_na_ziveenje.isEmpty() || telefonski_broj==null || telefonski_broj.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if(chovekRepository.existsById(embg)){
            throw new AlreadyExistsException();
        }
        Chovek c = new Chovek(embg,ime,prezime,datum_na_ragjanje,adresa_na_ziveenje,telefonski_broj);
        chovekRepository.save(c);
        return c;
    }

    @Override
    public Chovek findByEmbg(String embg) throws NotFound {
        Optional<Chovek> chovek =  chovekRepository.findById(embg);
        if(!chovek.isPresent()){
            throw new NotFound();
        }
        return chovek.get();
    }


}
