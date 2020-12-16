package library_project.lab.service.impl;

import library_project.lab.repository.PrimerokRepository;
import library_project.lab.service.PrimerokService;

public class PrimerokServiceImpl implements PrimerokService {

    private final PrimerokRepository primerokRepository;

    public PrimerokServiceImpl(PrimerokRepository primerokRepository) {
        this.primerokRepository = primerokRepository;
    }




}
