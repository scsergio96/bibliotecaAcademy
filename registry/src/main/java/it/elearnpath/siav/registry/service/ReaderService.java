package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Reader;
import it.elearnpath.siav.registry.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<ReaderDTO> getAllReaders() {
        List<Reader> readers = readerRepository.findAll();
        List<ReaderDTO> readersDTO = readers.stream()
                .map(reader -> {
                    ReaderDTO readerDTO = new ReaderDTO();
                    readerDTO.setId(reader.getId());
                    readerDTO.setName(reader.getName());
                    readerDTO.setSurname(reader.getSurname());
                    readerDTO.setCardNumber(reader.getCardNumber());
                    readerDTO.setAddress(reader.getAddress());
                    return readerDTO;
                }).collect(Collectors.toList());
        return readersDTO;
    }

    public ReaderDTO findReaderById(Integer id) throws Exception {
        Optional<Reader> readerOptional = readerRepository.findById(id);

        if (readerOptional.isPresent()) {
            Reader reader = readerOptional.get();

            ReaderDTO readerDTO = new ReaderDTO();
            readerDTO.setId(reader.getId());
            readerDTO.setName(reader.getName());
            readerDTO.setSurname(reader.getSurname());
            readerDTO.setCardNumber(reader.getCardNumber());
            readerDTO.setAddress(reader.getAddress());

            return readerDTO;
        } else {
            throw new Exception("Reader not found");
        }
    }

    public void saveReader(ReaderDTO readerDTO) {
        Reader reader = new Reader();
        reader.setName(readerDTO.getName());
        reader.setSurname(readerDTO.getSurname());
        reader.setCardNumber(readerDTO.getCardNumber());
        reader.setAddress(readerDTO.getAddress());

        readerRepository.save(reader);
    }

    public void updateEntity(ReaderDTO readerDTO) throws Exception {

        Optional<Reader> readerOptional = readerRepository.findById(readerDTO.getId());

        if (readerOptional.isPresent()) {
            Reader reader = readerOptional.get();
            if (readerDTO.getName() != null && !reader.getName().equals(readerDTO.getName())) {
                reader.setName(readerDTO.getName());
            }
            if (readerDTO.getSurname() != null && !reader.getSurname().equals(readerDTO.getSurname())) {
                reader.setSurname(readerDTO.getSurname());
            }
            if (readerDTO.getCardNumber() != null && !reader.getCardNumber().equals(readerDTO.getCardNumber())) {
                reader.setCardNumber(readerDTO.getCardNumber());
            }
            if (readerDTO.getAddress() != null && !reader.getAddress().equals(readerDTO.getAddress())) {
                reader.setAddress(readerDTO.getAddress());
            }
            readerRepository.save(reader);
        } else {
            throw new Exception("Reader not found");
        }

    }

    public void deleteReaderById(Integer id) throws Exception {

        Optional<Reader> readerOptional = readerRepository.findById(id);

        if (readerOptional.isPresent()) {
            readerRepository.delete(readerOptional.get());
        } else {
            throw new Exception("Reader not present");
        }

    }
}
