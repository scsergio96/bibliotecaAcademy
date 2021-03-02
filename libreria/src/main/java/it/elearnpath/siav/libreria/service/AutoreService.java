package it.elearnpath.siav.libreria.service;

import it.elearnpath.siav.libreria.dto.AutoreDTO;
import it.elearnpath.siav.libreria.exception.BindingException;
import it.elearnpath.siav.libreria.exception.NotFoundException;

import java.util.List;

public interface AutoreService {

    List<AutoreDTO> findAllPaging(Integer page, Integer pageSize);

    List<AutoreDTO> searchByIdOrNameOrSurname(Integer id, String name, String surname);

    void saveAuthor(AutoreDTO autoreDTO) throws BindingException;

    void updateAuthor(AutoreDTO autoreDTO) throws NotFoundException;

    void deleteAuthorById(Integer id) throws NotFoundException;
}
