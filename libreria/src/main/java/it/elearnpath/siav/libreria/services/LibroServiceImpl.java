package it.elearnpath.siav.libreria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.elearnpath.siav.libreria.entity.Libro;
import it.elearnpath.siav.libreria.repository.LibroRepository;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Optional<Libro> getLibro(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public void insLibro(Libro libro) {
        libroRepository.save(libro);
    }

    @Override
    public void delLibro(Libro libro) {
        libroRepository.delete(libro);
    }

    @Override
    public Page<Libro> getLibri(Pageable pageable) {
        Page<Libro> result = libroRepository.findAllByOrderByTitoloAsc(pageable);
        return result;
    }

    @Override
    public List<Libro> getLibri() {
        return libroRepository.findAll();
    }

}
