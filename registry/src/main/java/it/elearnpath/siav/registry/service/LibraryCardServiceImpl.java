package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.LibraryCardConverter;
import it.elearnpath.siav.registry.dto.LibraryCardDTO;
import it.elearnpath.siav.registry.entity.LibraryCard;
import it.elearnpath.siav.registry.exception.BadRequestException;
import it.elearnpath.siav.registry.repository.LibraryCardRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final LibraryCardRepository libraryCardRepository;

    public LibraryCardServiceImpl(LibraryCardRepository libraryCardRepository) {
        this.libraryCardRepository = libraryCardRepository;
    }

    @Override
    public LibraryCard createNewValidCard() {

        LibraryCard libraryCard = new LibraryCard();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 5);
        libraryCard.setExpiration(calendar.getTime());

        return libraryCardRepository.save(libraryCard);
    }

    @Override
    public LibraryCardDTO searchCardById(Integer id) throws BadRequestException {

        Optional<LibraryCard> libraryCard = libraryCardRepository.findById(id);

        return libraryCard.map(LibraryCardConverter::convert)
                .orElseThrow(() -> new BadRequestException("Card not present"));
    }
}
