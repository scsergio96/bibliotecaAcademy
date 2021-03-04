package it.elearnpath.siav.libreria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;

import it.elearnpath.siav.libreria.converter.CasaEditriceDtoToCasaEditrice;
import it.elearnpath.siav.libreria.converter.CasaEditriceToCasaEditriceDto;
import it.elearnpath.siav.libreria.dto.CasaEditriceDTO;
import it.elearnpath.siav.libreria.entity.CasaEditrice;
import it.elearnpath.siav.libreria.repository.CasaEditriceRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CasaEditriceServiceImplTest {

    @Mock
    CasaEditriceRepository repository;

    @Mock
    CasaEditriceToCasaEditriceDto toDTO;

    @Mock
    CasaEditriceDtoToCasaEditrice toDAO;

    @InjectMocks
    CasaEditriceServiceImpl service;

    private CasaEditrice casaEditrice;

    private CasaEditrice create(Integer id){

        CasaEditrice casaEditrice = new CasaEditrice();

        casaEditrice.setId(id);
        casaEditrice.setIndirizzo("test indirizzo");
        casaEditrice.setPIva("11111111111");
        casaEditrice.setRagioneSociale("test ragione sociale");

        return casaEditrice;
        
    }

    @BeforeEach
    void setUp() {
        casaEditrice = create(1);
    }

    @Test
    public void searchByIdTest() {
        
        Integer id = 1;

        Optional<CasaEditrice> casaEditriceOpt = Optional.of(casaEditrice);
        doReturn(casaEditriceOpt).when(repository).findById(id);
        // when(repository.findById(1)).thenReturn(casaEditriceOpt);

        CasaEditriceDTO search = service.searchById(id);

        assertEquals(search, toDTO.convert(casaEditrice));
        verify(repository, times(1)).findById(id);

    }

    // @Test
    // public void searchByRagSocialeTest() {

    //     when(repository.findByRagioneSocialeLike(anyString())).thenReturn(casaEditrice);
        
    //     CasaEditriceDTO search = service.searchByRagSociale(anyString());

    //     assertEquals("11111111111", search.getPIva());

    //     verify(repository).findById(any());

    // }

    // @Test
    // public void addNewCasaEdi() {

    //     when(repository.save(any())).thenReturn(casaEditrice);

    //     CasaEditriceDTO casaEditriceDTO = service.searchById(casaEditrice.getId());

    //     assertEquals(1, casaEditriceDTO.getId());

    // }
    
}