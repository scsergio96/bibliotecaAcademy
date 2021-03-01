package it.elearnpath.siav.libreria.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.ResultActions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LibroControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    String jsonData = "{\n  \"id\": 1,\n  \"isbn\": \"12345678911\",\n  \"titolo\": \"prova\",\n  \"pagine\": 100,\n  \"primaPubblicazione\": \"2000-10-10\",\n  \"ultimaStampa\": \"2010-10-10\",\n  \"descrizione\": \"Descrizione\",\n  \"casaEditrice\": \"Editore1\",\n  \"genere\": \"prova\",\n  \"ristampa\": 1,\n  \"posizione\": 1,\n  \"ripiano\": 1,\n  \"lingua\": \"Italiano\",\n  \"idAutore\": [\n    1\n  ],\n  \"nomeAutore\": [\n    \"nome\"\n  ],\n  \"cognomeAutore\": [\n    \"cognome\"\n  ],\n  \"isAvailable\": true\n}";

    @Test
    public void testSearchById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/id/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonData)).andReturn();

    }

    @Test
    public void testSearchByIsbn() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/isbn/12345678911").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonData)).andReturn();

    }

    private String isbn = "1";

    // @Test
    // public void testSearchByIsbnErr() throws Exception {

    //     mockMvc.perform(MockMvcRequestBuilders.get("/books/search/isbn/" + isbn)
    //     .contentType(MediaType.APPLICATION_JSON)
    //     .accept(MediaType.APPLICATION_JSON))        
    //     .andExpect(status().isBadRequest()
    //     .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
    //     .andExpect(content().json(jsonData)).andReturn();

    // }

    private String jsonArray = "[" + jsonData + "]"; 
    @Test
    public void testSearchByTitoloLike() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/titolo/Prova").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonArray)).andReturn();

    }

    @Test
    public void testSearchByGenere() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/genere/Prova").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonArray)).andReturn();

    }

    

}
