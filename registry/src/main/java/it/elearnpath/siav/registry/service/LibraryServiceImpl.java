package it.elearnpath.siav.registry.service;

import it.elearnpath.siav.registry.dto.BookDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final RestTemplate restTemplate;

    public LibraryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BookDTO searchBookById(Integer id) {
        String url = "http://localhost:8080/books/search/id/" + id;

        BookDTO bookDTO = new BookDTO();

        bookDTO = restTemplate.getForObject(url, BookDTO.class);

        return bookDTO;
    }

    @Override
    public Boolean switchBookIsAvailable(BookDTO bookDTO) {
        String url = "http://localhost:8080/books/update";

        bookDTO.setIsAvailable(!bookDTO.getIsAvailable());

        restTemplate.put(url, bookDTO);

        return bookDTO.getIsAvailable();
    }
}
