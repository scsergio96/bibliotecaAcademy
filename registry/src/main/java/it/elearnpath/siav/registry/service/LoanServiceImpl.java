package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.LoanConverter;
import it.elearnpath.siav.registry.dto.BookDTO;
import it.elearnpath.siav.registry.dto.LoanDTO;
import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Loan;
import it.elearnpath.siav.registry.exception.BadRequestException;
import it.elearnpath.siav.registry.repository.LoanRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    private final LoanRepository loanRepository;
    private final LibraryService libraryService;
    private final ReaderService readerService;

    public LoanServiceImpl(LoanRepository loanRepository, LibraryService libraryService, ReaderService readerService) {
        this.loanRepository = loanRepository;
        this.libraryService = libraryService;
        this.readerService = readerService;
    }

    /**
     * Simple search by readerId and bookId calling the query by example method in the repository.
     * Can be use to retrieve all the records if both params are null (not recommended).
     *
     * @param readerId can be null
     * @param bookId can be null
     * @return List(LoanDTO)
     */
    @Override
    public List<LoanDTO> searchByReaderIdBookId(Integer readerId, Integer bookId) {

        Loan loanExample = new Loan();
        loanExample.setIdReader(readerId);
        loanExample.setIdBook(bookId);

        List<Loan> loans = loanRepository.findAll(Example.of(loanExample));
        List<LoanDTO> loanDTOs = loans.stream()
                                      .map(LoanConverter::convert)
                                      .collect(Collectors.toList());
        return loanDTOs;
    }

    /**
     * Update a checked record present in the database. All the value will be overwrote.
     * Before saving the record the libraryService is called to find the book by its id and switching the isAvailable
     * flag
     *
     * CAUTION: Transactionality not safe TODO implementing saga pattern
     *
     * @param loanDTO
     * @return
     * @throws BadRequestException
     */
    @Override
    public LoanDTO updateLoanIfPresent(LoanDTO loanDTO) throws BadRequestException {

        Loan loan = LoanConverter.convert(loanDTO);
        Optional<Loan> oldLoan;

        if (loan.getId() != null) {
            oldLoan = loanRepository.findById(loan.getId());
        } else {
            throw new BadRequestException("Loan id cannot be null");
        }

        // TODO test actual functionality
        if (oldLoan.isPresent()) {
            if (oldLoan.get().getEnd() == null && loan.getEnd() != null) {
                BookDTO bookDTO = libraryService.searchBookById(loan.getIdBook());
                libraryService.switchBookIsAvailable(bookDTO);
            }
            loanRepository.save(loan);
            return LoanConverter.convert(loan);
        } else {
            throw new BadRequestException("Loan not present in the repository");
        }
    }

    /**
     * Helper method to save a record. If id (Loan) is present it will override the record
     *
     * @param loanDTO
     * @return Loan record saved in the DB
     */
    private Loan save(LoanDTO loanDTO) {

        Loan loan = LoanConverter.convert(loanDTO);

        return loanRepository.save(loan);
    }

    /**
     * Method to insert a valid Loan in the database.
     * Check if the reader (id) is present. Call the library service to check if the book is present and available.
     * Save the record in the database and switch the isAvailable flag calling the library service.
     *
     * CAUTION: Transactionality not safe TODO implementing saga pattern
     *
     * @param loanDTO
     * @return LoanDTO represent the actual record saved in the database
     * @throws BadRequestException in case a bookId or a cardNumber is not present in the loanDTO, the reader is not
     * present in the DB, the book is not available.
     */
    @Override
    public LoanDTO insertLoanByValidReaderCardNumberAndBookId(LoanDTO loanDTO) throws BadRequestException {

        Optional<Integer> bookId = Optional.ofNullable(loanDTO.getIdBook());
        BookDTO bookDTO = bookId.map(libraryService::searchBookById)
                .orElseThrow(() -> new BadRequestException("Book id required or not present in the database"));

        Optional<Integer> cardNumber = Optional.ofNullable(loanDTO.getCardNumber());
        ReaderDTO readerDTO = cardNumber.map(readerService::findByCardNumber)
                .orElseThrow(() -> new BadRequestException("Card number missing or not present in the database"));

        // TODO use the converter to return the saved Loan
        if (bookDTO.getIsAvailable()) {
            loanDTO.setIdReader(readerDTO.getId());
            Loan loan = save(loanDTO);
            loanDTO.setId(loan.getId());
            libraryService.switchBookIsAvailable(bookDTO);
            return loanDTO;
        } else {
            throw new BadRequestException("The book is not currently available");
        }
    }

    @Override
    public LoanDTO insertLoanByValidReaderIdAndBookId(LoanDTO loanDTO) throws BadRequestException {

        Optional<Integer> bookId = Optional.ofNullable(loanDTO.getIdBook());
        BookDTO bookDTO = bookId.map(libraryService::searchBookById)
                .orElseThrow(() -> new BadRequestException("Book id required or not present in the database"));

        Optional<Integer> readerId = Optional.ofNullable(loanDTO.getIdReader());
        ReaderDTO readerDTO = readerId.map(readerService::findReaderById)
                .orElseThrow(() -> new BadRequestException("Reader id missing or not present in the database"));

        if (bookDTO.getIsAvailable()) {
            Loan loan = save(loanDTO);
            libraryService.switchBookIsAvailable(bookDTO);
            return LoanConverter.convert(loan);
        } else {
            throw new BadRequestException("The book is not currently available");
        }
    }
}
