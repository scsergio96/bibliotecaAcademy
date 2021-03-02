package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.BookDTO;

public interface LibraryService {

    BookDTO searchBookById(Integer id);

    Boolean switchBookIsAvailable(BookDTO bookDTO);
}
