package it.elearnpath.siav.registry.repository;

import it.elearnpath.siav.registry.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {

    List<Reader> findAll();

    Reader findByCardNumber(Integer cardNumber);
}
