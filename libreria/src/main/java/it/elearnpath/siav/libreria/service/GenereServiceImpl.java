package it.elearnpath.siav.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.entity.Genere;
import it.elearnpath.siav.libreria.repository.GenereRepository;

@Service
public class GenereServiceImpl implements GenereService {

    @Autowired
    private GenereRepository genereRepository;

    @Override
    public Optional<Genere> getGenereById(Integer id) {
        return genereRepository.findById(id);
    }

    @Override
    public void addGenere(Genere genere) {
        genereRepository.save(genere);
    }

    @Override
    public void deleteGenere(Integer id) {
        genereRepository.deleteById(id);
    }

    @Override
    public List<Genere> getGeneri() {
        return genereRepository.findAll();
    }
    


}
