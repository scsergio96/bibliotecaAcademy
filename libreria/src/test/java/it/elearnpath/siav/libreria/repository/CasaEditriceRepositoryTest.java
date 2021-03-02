package it.elearnpath.siav.libreria.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.elearnpath.siav.libreria.entity.CasaEditrice;

@SpringBootTest
public class CasaEditriceRepositoryTest {


    @Autowired
    private CasaEditriceRepository repository;

    private CasaEditrice casaEditrice = create(1);

    private CasaEditrice create(Integer id){
        CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(id);
        casaEditrice.setIndirizzo("test indirizzo");
        casaEditrice.setPIva("11111111111");
        casaEditrice.setRagioneSociale("test ragione sociale");

        return casaEditrice;
    }

    @Test
    public void findByRagioneSocialeLikeTest() {
        repository.save(casaEditrice);

        CasaEditrice search = repository.findByRagioneSocialeLike("test ragione sociale");
        assertEquals("test ragione sociale", search.getRagioneSociale());
    }

    @Test
    public void findBypIvaLike(){
        repository.save(casaEditrice);

        CasaEditrice search = repository.findBypIvaLike("11111111111");
        assertEquals("11111111111", search.getPIva());
    }

}
