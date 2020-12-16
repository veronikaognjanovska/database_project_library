package library_project.lab.service.impl;

import library_project.lab.repository.PozajmicaRepository;
import library_project.lab.service.PozajmicaService;

public class PozajmicaServiceImpl implements PozajmicaService {

    private final PozajmicaRepository pozajmicaRepository;

    public PozajmicaServiceImpl(PozajmicaRepository pozajmicaRepository) {
        this.pozajmicaRepository = pozajmicaRepository;
    }



}
