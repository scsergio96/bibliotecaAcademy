package it.elearnpath.siav.libreria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

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
@Disabled
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

        Optional<CasaEditrice> casaEditriceOpt = Optional.ofNullable(casaEditrice);
        //doReturn(casaEditriceOpt).when(repository).findById(id);
        when(repository.findById(id)).thenReturn(casaEditriceOpt);

        CasaEditriceDTO search = service.searchById(id);

        assertSame(search, toDTO.convert(casaEditrice));

        verify(repository, times(1)).findById(id);

    }

    @Test
    public void searchByRagSocialeTest() {

        when(repository.findByRagioneSocialeLike("test ragione sociale")).thenReturn(casaEditrice);
     
        CasaEditriceDTO search = service.searchByRagSociale("test ragione sociale");

        assertEquals(search, toDTO.convert(casaEditrice));
        verify(repository, times(1)).findByRagioneSocialeLike("test ragione sociale");

    }


    @Test
    public void searchPIvaTest() {

        when(repository.findBypIvaLike("11111111111")).thenReturn(casaEditrice);
     
        CasaEditriceDTO search = service.searchByRagSociale("test ragione sociale");

        assertEquals(search, toDTO.convert(casaEditrice));
        verify(repository, times(1)).findBypIvaLike("11111111111");

    }


    @Test
    public void searchByIdOrRagSocialeOrPivaTest() throws Exception {

        List<CasaEditrice> casaEditriceList = new ArrayList<>();
        casaEditriceList.add(create(1));
        casaEditriceList.add(create(2));

        when(repository.findAll(Example.of(casaEditrice))).thenReturn(casaEditriceList);


        List<CasaEditriceDTO> casaEditriceDTOList = service.searchByIdOrRagSocialeOrPiva(1, "test ragione sociale", "11111111111");

        assertEquals(1, casaEditriceDTOList.size());
    }


    @Test
    public void addNewCasaEdi() {

        when(repository.save(any())).thenReturn(casaEditrice);

        CasaEditriceDTO casaEditriceDTO = service.searchById(casaEditrice.getId());

        assertNotNull(casaEditriceDTO);

    }

}