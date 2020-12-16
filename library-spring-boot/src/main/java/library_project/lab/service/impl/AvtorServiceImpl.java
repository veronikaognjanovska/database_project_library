package library_project.lab.service.impl;

import library_project.lab.model.Avtor;
import library_project.lab.model.Nastan;
import library_project.lab.model.exception.AlreadyExistsException;
import library_project.lab.repository.AvtorRepository;
import library_project.lab.service.AvtorService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvtorServiceImpl implements AvtorService {

    private final AvtorRepository avtorRepository;

    public AvtorServiceImpl(AvtorRepository avtorRepository) {
        this.avtorRepository = avtorRepository;
    }

    @Override
    public Avtor save(String ime, String prezime, Integer godina_na_ragjanje)
            throws IllegalArgumentException,AlreadyExistsException{
        if (ime==null || ime.isEmpty() || prezime==null || prezime.isEmpty() ||
                godina_na_ragjanje==null) {
            throw new IllegalArgumentException();
        }
        if(avtorRepository.findByImeAndAndPrezimeAndGodina(ime, prezime, godina_na_ragjanje).isPresent()){
            throw new AlreadyExistsException();
        }

        return avtorRepository.save(new Avtor(ime,prezime,godina_na_ragjanje));
    }
    @Override
    public Avtor findById(Long id) throws NotFound {
        Optional<Avtor> avtor =  avtorRepository.findById(id);
        if(!avtor.isPresent()){
            throw new NotFound();
        }
        return avtor.get();
    }
}
