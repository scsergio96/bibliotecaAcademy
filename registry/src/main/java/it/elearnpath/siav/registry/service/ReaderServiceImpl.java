package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.ReaderConverter;
import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.LibraryCard;
import it.elearnpath.siav.registry.entity.Reader;
import it.elearnpath.siav.registry.exception.BadRequestException;
import it.elearnpath.siav.registry.repository.ReaderRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImpl implements ReaderService{

    private final ReaderRepository readerRepository;
    private final LibraryCardService libraryCardService;

    public ReaderServiceImpl(ReaderRepository readerRepository, LibraryCardService libraryCardService){
        this.readerRepository = readerRepository;
        this.libraryCardService = libraryCardService;
    }

    /**
     * Call to the repository for a simple select all
     *
     * @return List(ReaderDTO)
     */
    @Override
    public List<ReaderDTO> getAllReaders() {

        List<Reader> readers = readerRepository.findAll();

        List<ReaderDTO> readersDTO = readers.stream()
                                            .map(ReaderConverter::convert)
                                            .collect(Collectors.toList());
        return readersDTO;
    }


    /**
     * Search by id, cardNumber, name or surname calling the query by example method in the reader repository.
     *
     * @param id can be null
     * @param cardNumber can be null
     * @param name can be null
     * @param surname can be null
     * @return List(ReaderDTO)
     */
    @Override
    public List<ReaderDTO> searchByIdOrCardNumberOrNameOrSurname(Integer id, Integer cardNumber, String name, String surname) {
        Reader readerExample = new Reader();
        readerExample.setId(id);
        readerExample.setCardNumber(cardNumber);
        readerExample.setName(name);
        readerExample.setSurname(surname);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", nome -> nome.ignoreCase().contains())
                .withMatcher("surname", cognome -> cognome.ignoreCase().contains());

        List<Reader> readers = readerRepository.findAll(Example.of(readerExample, matcher));
        List<ReaderDTO> readerDTOs = new ArrayList<>();

        readerDTOs = readers.stream()
                .map(ReaderConverter::convert)
                .collect(Collectors.toList());

        return readerDTOs;
    }

    @Override
    public ReaderDTO findReaderById(Integer id){

        Optional<Reader> readerOptional = readerRepository.findById(id);

        if (readerOptional.isPresent()) {
            ReaderDTO readerDTO = ReaderConverter.convert(readerOptional.get());

            return readerDTO;
        }else{
            return null;
        }
    }


    /**
     * Insert a new record in the reader database. Id must be null.
     *
     * @param readerDTO
     * @return ReaderDto converted from Reader just saved in the DB
     * @throws BadRequestException
     */
    @Override
    public ReaderDTO insertNewReader(ReaderDTO readerDTO) throws BadRequestException {

        if (readerDTO.getId() != null) {
            throw new BadRequestException("The reader id should not be passed or must be null");
        }

        if (readerDTO.getCardNumber() != null) {
            throw new BadRequestException("The card number should not be passed or must be null");
        }

        LibraryCard libraryCard = libraryCardService.createNewValidCard();
        readerDTO.setCardNumber(libraryCard.getId());

        Reader savedReader = saveReader(readerDTO);

        return ReaderConverter.convert(savedReader);
    }

    /**
     * Update Reader if id is not null and the record is present in the DB. All the value will be overwrote.
     *
     * @param readerDTO
     * @return ReaderDTO
     * @throws BadRequestException
     */
    @Override
    public ReaderDTO updateEntity(ReaderDTO readerDTO) throws BadRequestException {

        Optional<Reader> oldReader;

        if (readerDTO.getId() != null) {
            oldReader = readerRepository.findById(readerDTO.getId());
        } else {
            throw new BadRequestException("The id cannot be null");
        }

        if (oldReader.isPresent()) {
            Reader reader = ReaderConverter.convertWithId(readerDTO);
            Reader savedReader = readerRepository.save(reader);
            return ReaderConverter.convert(savedReader);
        } else {
            throw new BadRequestException("Reader not present in the database");
        }
    }

    /**
     * Helper method to save a record. If id (Loan) is present it will override the record
     *
     * @param readerDTO
     * @return Reader record saved in the DB
     */
    private Reader saveReader(ReaderDTO readerDTO) {

        Reader reader = ReaderConverter.convert(readerDTO);

        return readerRepository.save(reader);
    }

    /**
     * Delete a Reader if present in the DB.
     *
     * @param id
     * @return ReaderDTO
     * @throws BadRequestException
     */
    @Override
    public ReaderDTO deleteReaderById(Integer id) throws BadRequestException {

        Optional<Reader> readerOptional = readerRepository.findById(id);

        if (readerOptional.isPresent()) {
            readerRepository.delete(readerOptional.get());
        } else {
            throw new BadRequestException("Reader not present in the DB");
        }

        return ReaderConverter.convert(readerOptional.get());
    }

    @Override
    public ReaderDTO findByCardNumber(Integer cardNumber) {
        Reader reader = readerRepository.findByCardNumber(cardNumber);

        if(reader == null){
            return null;
        }

        ReaderDTO readerDTO = ReaderConverter.convert(reader);

        return readerDTO;
    }
}
