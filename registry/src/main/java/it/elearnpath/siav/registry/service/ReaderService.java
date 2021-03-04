package it.elearnpath.siav.registry.service;

import java.util.List;
import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.exception.BadRequestException;

public interface ReaderService {

    List<ReaderDTO> getAllReaders();

    ReaderDTO findReaderById(Integer id);

    ReaderDTO findByCardNumber(Integer cardNumber);

//    void saveReader(ReaderDTO readerDTO);

    ReaderDTO updateEntity(ReaderDTO readerDTO) throws BadRequestException;

    ReaderDTO deleteReaderById(Integer id) throws BadRequestException;

    List<ReaderDTO> searchByIdOrCardNumber(Integer id, Integer cardNumber);

    ReaderDTO insertNewReader(ReaderDTO readerDTO) throws BadRequestException;
}