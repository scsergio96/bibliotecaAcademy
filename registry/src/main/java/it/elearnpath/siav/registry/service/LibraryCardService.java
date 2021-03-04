package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.LibraryCardDTO;
import it.elearnpath.siav.registry.entity.LibraryCard;
import it.elearnpath.siav.registry.exception.BadRequestException;

public interface LibraryCardService {

    LibraryCard createNewValidCard();

    LibraryCardDTO searchCardById(Integer id) throws BadRequestException;
}
