package it.elearnpath.siav.registry.repository;

import it.elearnpath.siav.registry.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    List<Reader> findAll();

    Optional<Reader> findById(Integer id);

}
