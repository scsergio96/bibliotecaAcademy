package it.elearnpath.siav.libreria.service;

import java.util.List;
import java.util.Optional;

import it.elearnpath.siav.libreria.entity.Genere;

public interface GenereService {
    
    public Optional<Genere> getGenereById(Integer id);

    public void addGenere(Genere genere);

    public void deleteGenere(Integer id);

    public List<Genere> getGeneri();

}
