package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.entity.LibraryCard;
import it.elearnpath.siav.registry.repository.LibraryCardRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;

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
}
