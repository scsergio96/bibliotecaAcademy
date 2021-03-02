package it.elearnpath.siav.libreria.repository;

import it.elearnpath.siav.libreria.entity.CasaEditrice;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CasaEditriceRepository extends JpaRepository<CasaEditrice, Integer> {

    Optional<CasaEditrice> findById(Integer id);

    CasaEditrice findByRagioneSocialeLike(String ragioneSociale);

    CasaEditrice findBypIvaLike(String pIva);

}
