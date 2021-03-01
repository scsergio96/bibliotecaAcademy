package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.converter.ReaderConverter;
import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.entity.Reader;
import it.elearnpath.siav.registry.repository.ReaderRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderServiceImpl implements ReaderService{

    private final ReaderRepository readerRepository;

    public ReaderServiceImpl(ReaderRepository readerRepository){
        this.readerRepository = readerRepository;
    }

    @Override
    public List<ReaderDTO> getAllReaders() {

        List<Reader> readers = readerRepository.findAll();

        List<ReaderDTO> readersDTO = readers.stream()
                                            .map(ReaderConverter::convert)
                                            .collect(Collectors.toList());
        return readersDTO;
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

	@Override
	public ReaderDTO findByCardNumber(Integer cardNumber) {
		Reader reader = readerRepository.findByCardNumber(cardNumber);

        if(reader == null){
            return null;
        }

        ReaderDTO readerDTO = ReaderConverter.convert(reader);

        return readerDTO;
	}

    @Override
    public void saveReader(ReaderDTO readerDTO) {

        Reader reader = ReaderConverter.convert(readerDTO);
        readerRepository.save(reader);

    }

    @Override
    public void updateEntity(ReaderDTO readerDTO) {

        Reader reader = ReaderConverter.convertWithId(readerDTO);
        if (!reader.getName().equals(readerDTO.getName())) {
            reader.setName(readerDTO.getName());
        }
        if (!reader.getSurname().equals(readerDTO.getSurname())) {
            reader.setSurname(readerDTO.getSurname());
        }
        if (!reader.getCardNumber().equals(readerDTO.getCardNumber())) {
            reader.setCardNumber(readerDTO.getCardNumber());
        }
        if (!reader.getAddress().equals(readerDTO.getAddress())) {
            reader.setAddress(readerDTO.getAddress());
        }
        readerRepository.save(reader);
    }

    @Override
    public void deleteReaderById(Integer id){

        Optional<Reader> readerOptional = readerRepository.findById(id);

        readerRepository.delete(readerOptional.get());

    }

    @Override
    public List<ReaderDTO> searchByIdOrCardNumber(Integer id, Integer cardNumber) {
        Reader readerExample = new Reader();
        readerExample.setId(id);
        readerExample.setCardNumber(cardNumber);

        List<Reader> readers = readerRepository.findAll(Example.of(readerExample));
        List<ReaderDTO> readerDTOs = new ArrayList<>();

        readerDTOs = readers.stream()
                .map(ReaderConverter::convert)
                .collect(Collectors.toList());

        return readerDTOs;
    }
}
