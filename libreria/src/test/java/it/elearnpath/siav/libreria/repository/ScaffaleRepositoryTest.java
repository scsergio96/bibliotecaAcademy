package it.elearnpath.siav.libreria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.elearnpath.siav.libreria.entity.Scaffale;

@SpringBootTest
public class ScaffaleRepositoryTest {

    @Autowired
    private ScaffaleRepository repository;

    private Scaffale scaffale = null;

    private Scaffale create(Integer id) {
        Scaffale scaffale = new Scaffale();

        scaffale.setId(id);
        scaffale.setNumero(1);
        scaffale.setRipiano(1);

        return scaffale;
    }

    @Test
    public void findByNumeroAndRipianoTest() throws Exception {
        scaffale = create(1);

        Scaffale searchByNumAndRip = repository.findByNumeroAndRipiano(1, 1);

        assertEquals(1, searchByNumAndRip.getNumero());
        assertEquals(1, searchByNumAndRip.getRipiano());
    }
    
}
