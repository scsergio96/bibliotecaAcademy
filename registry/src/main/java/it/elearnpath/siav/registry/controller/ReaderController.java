package it.elearnpath.siav.registry.controller;

import it.elearnpath.siav.registry.dto.ReaderDTO;
import it.elearnpath.siav.registry.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

                                                                /*
                                                                    Metodi di accesso ai dati
                                                                */

    @GetMapping("/all")
    public ResponseEntity<List<ReaderDTO>> getAllReaders() {

        List<ReaderDTO> readers = readerService.getAllReaders();

        if(readers.isEmpty()){
            return new ResponseEntity<List<ReaderDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ReaderDTO>>(readers, HttpStatus.OK);

    }


    @GetMapping("/search/{id}")
    public ResponseEntity<ReaderDTO> searchReaderById(@PathVariable Integer id) throws Exception {

        ReaderDTO readerDTO = readerService.findReaderById(id);

        if(readerDTO == null){
            throw new Exception("Elemento non presente");
        }

        return new ResponseEntity<ReaderDTO>(readerDTO, HttpStatus.OK);

    }

    @GetMapping("/search/{cardNumber}")
    public ResponseEntity<ReaderDTO> searchReaderByCodNum(@PathVariable String cardNumber) throws Exception {

        ReaderDTO readerDTO = readerService.findByCardNumber(cardNumber)

        if(readerDTO == null){
            throw new Exception("Elemento non presente");
        }

        return new ResponseEntity<ReaderDTO>(readerDTO, HttpStatus.OK);

    }

                                                                /*
                                                                    Metodi di modifica dei dati
                                                                */

    @PostMapping("/add")
    public ResponseEntity<?> insertReader(@RequestBody @Valid ReaderDTO readerDTO) {

        readerService.saveReader(readerDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateReader(@RequestBody @Valid ReaderDTO readerDTO) throws Exception {

        readerService.updateEntity(readerDTO);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReader(@PathVariable Integer id) throws Exception {

        readerService.deleteReaderById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
