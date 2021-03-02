package it.elearnpath.siav.libreria.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import it.elearnpath.siav.libreria.service.CasaEditriceService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CasaEditriceControllerTest {

    // Essendo che ogni volta che viene aggiunto un nuovo elemento l'id che viene fornito a questo sarà pari a ultimoId+1 sarà necessario effettuare ogni voglta l'operazione di 
    // ALTER TABLE casa_editrice AUTO_INCREMENT = 1 (in questo caso il db conterrà un solo elemento prima dell'esecuzione dei test) 

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private CasaEditriceService service;

    private String baseUrl = "/editors";

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    String jsonData = "    {\r\n        \"id\": 1,\r\n        \"ragioneSociale\": \"Editore1\",\r\n        \"indirizzo\": \"Via Roma\",\r\n        \"piva\": \"11111111111\"\r\n    }";


    @Test
    @Order(3) 
    public void getByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/id/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonData))
               .andReturn();

    }

    private Integer id = 10000;

    @Test
    @Order(4) 
    public void getByIdErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/id/" + id)
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("non è presente una casa editrice con id pari a " + id))
               .andDo(print());
               
    }


    @Test
    @Order(5) 
    public void getByRagioneSocialeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/ragioneSoc/Editore1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonData))
               .andReturn();

    }

    private String ragSoc = "Editore25555";

    @Test
    @Order(6) 
    public void getByRagioneSocialeErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/ragioneSoc/" + ragSoc)
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Non è presente alcuna casa editrice che abbia questa ragione sociale " + ragSoc))
               .andDo(print());
               
    }

    @Test
    @Order(7) 
    public void getByPIvaTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/piva/11111111111")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonData))
               .andReturn();

    }

    private String pIva = "00000000000";

    @Test
    @Order(8) 
    public void getByPivaErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/piva/" + pIva)
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Non è presente alcuna casa editrice che abbia questa partita iva " + pIva))
               .andDo(print());
               
    }


    private String pIva2 = "000000";

    @Test
    @Order(9) 
    public void getByPivaErrTest2() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/piva/" + pIva2)
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.codice").value(400))
               .andExpect(jsonPath("$.messaggio").value("la lunghezza della partita iva deve essere pari a 11"))
               .andDo(print());
               
    }

    @Test
    @Order(10) 
    public void getAllTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/all")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andReturn();

    }

    String jsonDataAdd = "    {\r\n        \"id\": 2,\r\n        \"ragioneSociale\": \"Editore2\",\r\n        \"indirizzo\": \"Via Roma\",\r\n        \"piva\": \"22222222222\"\r\n    }";

    @Test
    @Order(1) 
    public void addNewElementTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataAdd)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andDo(print());
    }
    
    @Test
    @Order(2) 
    public void addNewElementErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataAdd)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotAcceptable())
               .andExpect(jsonPath("$.codice").value(406))
               .andExpect(jsonPath("$.messaggio").value("Elemento gia presente"))
               .andDo(print());
    }  

    String jsonData2 = "    {\r\n        \"id\": 2,\r\n        \"ragioneSociale\": \"Editore3\",\r\n        \"indirizzo\": \"Via Roma\",\r\n        \"piva\": \"33333333333\"\r\n    }";


    @Test
    @Order(11) 
    public void updateElementTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData2)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andDo(print());

    }

    String jsonDataErr = "    {\r\n        \"id\": 20000,\r\n        \"ragioneSociale\": \"Editore20\",\r\n        \"indirizzo\": \"Via Roma\",\r\n        \"piva\": \"33333333333\"\r\n    }";

    @Test
    @Order(12) 
    public void updateElementErrTest() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataErr)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Non è presente alcuna casa editrice con id pari a 20000"))
               .andDo(print());

    }


    String jsonDataErr2 = "    {\r\n        \"id\": 2,\r\n        \"ragioneSociale\": \"Editore3\",\r\n        \"indirizzo\": \"Via Roma\",\r\n        \"piva\": \"11111111111\"\r\n    }";


    @Test
    @Order(13) 
    public void updateElementErrTest2() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataErr2)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.codice").value(400))
               .andExpect(jsonPath("$.messaggio").value("Questa partita iva è gia presente"))
               .andDo(print());

    }

    @Test
    @Order(14) 
    public void deleteElement() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/delete/2")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());

    }

    @Test
    @Order(15)
    public void deleteErrElement() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/delete/20000")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Casa editrice 20000 non presente !"))
               .andDo(print());

    }
    
    
}
