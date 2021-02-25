package it.elearnpath.siav.libreria.service;

import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.exception.ErrorResponse;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.repository.AutoreRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe di servizio per AutoreController
 */
@Service
public class AutoreService {

    private final AutoreRepository autoreRepository;


    public AutoreService(AutoreRepository autoreRepository) {
        this.autoreRepository = autoreRepository;
    }

    /**
     * GET "/authors/all/search/:page
     * Richiesta al database della lista di tutti gli autori attraverso sistema di paginazione.
     * Conversione di una lista di Author in una di AuthorDTO
     *
     * @param page numero della pagina da restituire
     * @return List<AuthorDTO>
     */
    public List<AutoreDTO> findAllPaging(int page) {
        Pageable pageNumber = PageRequest.of(page, 2);
        List<Autore> autori = autoreRepository.findByOrderByCognomeAscNomeAsc(pageNumber);
        List<AutoreDTO> autoriDTO = new ArrayList<>();

        autoriDTO = autori.stream()
            .map(autore -> {
                AutoreDTO autoreDTO = new AutoreDTO();
                autoreDTO.setId(autore.getId());
                autoreDTO.setNome(autore.getNome());
                autoreDTO.setCognome(autore.getCognome());
                autoreDTO.setBiografia(autore.getBiografia());
                autoreDTO.setNazionalita(autore.getNazionalita());
                autoreDTO.setDataNascita(dateConverter(autore.getDataNascita()));
                autoreDTO.setDataMorte(dateConverter(autore.getDataMorte()));
                return autoreDTO;
            })
            .collect(Collectors.toList());

        return autoriDTO;
    }

    /**
     * Converte un valore Date nel corrispettivo valore String formato dd-MM-yyyy
     *
     * @param date data in formato Date da convertire
     * @return String corrispettivo valore String
     */
    private String dateConverter(Date date) {
        if(date != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(date).toString();
        } else return null;
    }

    /**
     * GET "/authors/search/:id"
     * Richiesta al database di un autore tramite id.
     * Conversione Optional(Autore) in AutoreDTO
     *
     * @param id valore della chiave primaria
     * @return AutoreDTO
     * @throws NotFoundException se l'autore non è presente nel database
     */
    public AutoreDTO getAuthorById(Integer id) throws NotFoundException {
        AutoreDTO autoreDTO = new AutoreDTO();
        Autore autore = new Autore();

        Optional<Autore> autoreOptional = autoreRepository.findById(id);

        if(autoreOptional.isPresent()) {
            autore = autoreOptional.get();
            autoreDTO.setId(autore.getId());
            autoreDTO.setNome(autore.getNome());
            autoreDTO.setCognome(autore.getCognome());
            autoreDTO.setBiografia(autore.getBiografia());
            autoreDTO.setNazionalita(autore.getNazionalita());
            autoreDTO.setDataNascita(dateConverter(autore.getDataNascita()));
            autoreDTO.setDataMorte(dateConverter(autore.getDataMorte()));
        } else {
            throw new NotFoundException();
        }

        return autoreDTO;
    }

    /**
     * POST "/authors/add"
     * Trasformazione oggetto AutoreDTO in oggeto Autore.
     * La gestione dei constraits è effettuata nella classe Autore
     *
     * @param autoreDTO
     */
    public void saveAuthor(AutoreDTO autoreDTO) {
        Autore autore = new Autore();
        autore.setNome(autoreDTO.getNome());
        autore.setCognome(autoreDTO.getCognome());
        autore.setDataNascita(autoreDTO.getDataNascita());
        autore.setDataMorte(autoreDTO.getDataMorte());
        autore.setBiografia(autoreDTO.getBiografia());
        autore.setNazionalita(autoreDTO.getNazionalita());

        autoreRepository.save(autore);
    }

    // TODO finire questo questo too
    public void updateAuthor(AutoreDTO autoreDTO) throws Exception {
        Integer id = autoreDTO.getId();
        if (id == null || id < 0) {
            throw new Exception("Id non valido");
        }

        Optional<Autore> autoreOptional = autoreRepository.findById(autoreDTO.getId());

        if (autoreOptional.isPresent()) {
            Autore autore = autoreOptional.get();
            if (autore.getNome() != null && autore.getNome() != autoreDTO.getNome()) {
                autore.setNome(autoreDTO.getNome());
            } else if (autoreDTO.getCognome() != null && autore.getCognome() != autoreDTO.getCognome()) {
                autore.setCognome(autoreDTO.getCognome());
            } else if (autoreDTO.getNazionalita() != null && autore.getNazionalita() != autoreDTO.getNazionalita()) {
                autore.setNazionalita(autoreDTO.getNazionalita());
            } else if (autoreDTO.getBiografia() != null && autore.getBiografia() != autoreDTO.getBiografia()) {
                autore.setBiografia(autoreDTO.getBiografia());
            } else if (autoreDTO.getDataNascita() != null && dateConverter(autore.getDataNascita()) != autoreDTO.getDataNascita()) {
                autore.setDataNascita(autoreDTO.getDataNascita());
            } else if (autoreDTO.getDataMorte() != null && dateConverter(autore.getDataMorte()) != autoreDTO.getDataMorte()) {
                autore.setDataMorte(autoreDTO.getDataMorte());
            }
        } else {
            throw new NotFoundException();
        }

    }
}
