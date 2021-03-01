package it.elearnpath.siav.registry.service;

import java.util.List;
import it.elearnpath.siav.registry.dto.ReaderDTO;

public interface ReaderService {

    List<ReaderDTO> getAllReaders();

    ReaderDTO findReaderById(Integer id);

    ReaderDTO findByCardNumber(Integer cardNumber);

    void saveReader(ReaderDTO readerDTO);

    void updateEntity(ReaderDTO readerDTO);

    void deleteReaderById(Integer id);

}