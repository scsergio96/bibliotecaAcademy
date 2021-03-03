package it.elearnpath.siav.registry.repository;

import it.elearnpath.siav.registry.entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Integer> {
}
