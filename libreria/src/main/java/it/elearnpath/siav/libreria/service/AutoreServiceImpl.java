package it.elearnpath.siav.libreria.service;

import it.elearnpath.siav.libreria.converter.AutoreConverter;
import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.entity.Autore;
import it.elearnpath.siav.libreria.exception.BindingException;
import it.elearnpath.siav.libreria.exception.NotFoundException;
import it.elearnpath.siav.libreria.repository.AutoreRepository;
import org.springframework.data.domain.Example;
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
public class AutoreServiceImpl implements AutoreService {

    private final AutoreRepository autoreRepository;


    public AutoreServiceImpl(AutoreRepository autoreRepository) {
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
    @Override
    public List<AutoreDTO> findAllPaging(Integer page, Integer pageSize) {
        Pageable pageNumber = PageRequest.of(page, pageSize);
        List<Autore> autori = autoreRepository.findByOrderByCognomeAscNomeAsc(pageNumber);
        List<AutoreDTO> autoriDTO = new ArrayList<>();

        autoriDTO = autori.stream()
                .map(AutoreConverter::convert)
                .collect(Collectors.toList());

        return autoriDTO;
    }

    /**
     * GET "/authors/search?id=&nome=&cognome=
     * Richiesta al database della lista di tutti gli autori attraverso sistema di paginazione.
     * Conversione di una lista di Author in una di AuthorDTO
     *
     * @return List<AuthorDTO>
     */
    @Override
    public List<AutoreDTO> searchByIdOrNameOrSurname(Integer id, String name, String surname) {
        Autore autoreExample = new Autore();
        autoreExample.setId(id);
        autoreExample.setNome(name);
        autoreExample.setCognome(surname);

        List<Autore> autori = autoreRepository.findAll(Example.of(autoreExample));
        List<AutoreDTO> autoriDTO = new ArrayList<>();

        autoriDTO = autori.stream()
            .map(AutoreConverter::convert)
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
        Optional<Autore> autoreOptional = autoreRepository.findById(id);

        if(autoreOptional.isPresent()) {
            return AutoreConverter.convert(autoreOptional.get());
        } else {
            throw new NotFoundException();
        }
    }

    /**
     * POST "/authors/add"
     * Trasformazione oggetto AutoreDTO in oggeto Autore.
     * La gestione dei constraits è effettuata nella classe Autore
     *
     * @param autoreDTO
     */
    @Override
    public void saveAuthor(AutoreDTO autoreDTO) throws BindingException {
        if (autoreDTO.getId() != null) {
            throw new BindingException("Il campo id deve essere nullo o non essere presente");
        }

        Autore autore = AutoreConverter.convert(autoreDTO);

        autoreRepository.save(autore);
    }

    /**
     * PUT /authors/update
     * Verifico la presenza dell'autore nel DB per id.
     * Verica parametro per parametro di AutoreDTO e Autore.
     *
     * @param autoreDTO
     * @throws NotFoundException quando l'autore da modificare non è presente nel database
     */
    @Override
    public void updateAuthor(AutoreDTO autoreDTO) throws NotFoundException {
        Optional<Autore> autoreOptional = autoreRepository.findById(autoreDTO.getId());

        if (autoreOptional.isPresent()) {
            Autore autore = AutoreConverter.convert(autoreDTO);

            autoreRepository.save(autore);
        } else {
            throw new NotFoundException();
        }

    }

    /**
     * DELETE /authors/delete/:id
     * Verifica presenza autore nel DB tramite id
     * Elima autore se presente.
     *
     * @param id
     * @throws NotFoundException se l'autore non è presente nel DB
     */
    @Override
    public void deleteAuthorById(Integer id) throws NotFoundException {
        Optional<Autore> autoreOptional = autoreRepository.findById(id);

        if (autoreOptional.isPresent()) {
            autoreRepository.delete(autoreOptional.get());
        } else {
            throw new NotFoundException();
        }
    }
}
