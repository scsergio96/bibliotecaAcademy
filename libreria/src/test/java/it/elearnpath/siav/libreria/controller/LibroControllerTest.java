package it.elearnpath.siav.libreria.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import it.elearnpath.siav.libreria.service.CasaEditriceService;

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
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonData))
                .andReturn();

    }

    private String isbn = "12345678901023333333333333333333333";

    @Test
    public void testSearchByIsbnErr() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/isbn/" + isbn).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
                .andExpect(jsonPath("$.messaggio").value("Il libro con isbn " + isbn + " non e stato trovato!"))
                .andDo(print());

    }

    private String jsonTitolo = "[\n  {\n    \"id\": 16,\n    \"isbn\": \"12345678920\",\n    \"titolo\": \"fasddas\",\n    \"pagine\": 5,\n    \"primaPubblicazione\": \"2000-10-09\",\n    \"ultimaStampa\": \"2010-10-09\",\n    \"descrizione\": \"Descrizione\",\n    \"casaEditrice\": \"Editore1\",\n    \"genere\": \"Genereiuokpo\",\n    \"ristampa\": null,\n    \"posizione\": -1,\n    \"ripiano\": -1,\n    \"lingua\": \"Italiano\",\n    \"idAutore\": [\n      11\n    ],\n    \"nomeAutore\": [\n      \"nome11\"\n    ],\n    \"cognomeAutore\": [\n      \"cognome11\"\n    ],\n    \"isAvailable\": null\n  }\n]";

    @Test
    public void testSearchByTitoloLike() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/titolo/fasddas").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonTitolo)).andReturn();

    }

    private String jsonArray = "[" + jsonData + "]";

    @Test
    public void testSearchByGenere() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/books/search/genere/Prova").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonArray)).andReturn();

    }

    String jsonAdd = "{\n  \"isbn\": \"012345678910\",\n  \"titolo\": \"prova junit\",\n  \"pagine\": 5,\n  \"primaPubblicazione\": \"2000-10-10\",\n  \"ultimaStampa\": \"2010-10-10\",\n  \"descrizione\": \"Descrizione\",\n  \"casaEditrice\": \"Editore3\",\n  \"genere\": \"Genere\",\n  \"posizione\": \"5\",\n  \"ripiano\": \"5\",\n  \"lingua\": \"Italiano\",\n  \"idAutore\": [\n    11\n  ]\n}";

    @Test
    public void testAdd() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/books/add").contentType(MediaType.APPLICATION_JSON)
                .content(jsonAdd).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

    }

    String jsonAddErr = "{\n  \"isbn\": \"012\",\n  \"titolo\": \"prova junit\",\n  \"pagine\": 5,\n  \"primaPubblicazione\": \"2000-10-10\",\n  \"ultimaStampa\": \"2010-10-10\",\n  \"descrizione\": \"Descrizione\",\n  \"casaEditrice\": \"Editore3\",\n  \"genere\": \"Genere\",\n  \"posizione\": \"5\",\n  \"ripiano\": \"5\",\n  \"lingua\": \"Italiano\",\n  \"idAutore\": [\n    11\n  ]\n}";

    @Test
    public void testAddErr() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/books/add").contentType(MediaType.APPLICATION_JSON)
            .content(jsonAddErr)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.codice").value(400))
            .andExpect(jsonPath("$.messaggio").value("L isbn puo contenere minimo 10 caratteri e al massimo 20 caratteri"))
            .andDo(print());
    }

    String jsonUpdate = "{\n  \"id\": 2,\n  \"isbn\": \"1158519514897\",\n  \"titolo\": \"update junit\",\n  \"pagine\": 5,\n  \"primaPubblicazione\": \"2000-10-10\",\n  \"ultimaStampa\": \"2010-10-10\",\n  \"descrizione\": \"Descrizione\",\n  \"casaEditrice\": \"Editore3\",\n  \"genere\": \"Genere\",\n  \"posizione\": \"25\",\n  \"ripiano\": \"5\",\n  \"lingua\": \"Italiano\",\n  \"idAutore\": [\n    11\n  ]\n}";

    @Test
    public void testUpdate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/books/update").contentType(MediaType.APPLICATION_JSON)
            .content(jsonUpdate)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

    }


    String jsonUpdateErr = "{\n  \"id\": 2444,\n  \"isbn\": \"1158519514897\",\n  \"titolo\": \"update junit\",\n  \"pagine\": 5,\n  \"primaPubblicazione\": \"2000-10-10\",\n  \"ultimaStampa\": \"2010-10-10\",\n  \"descrizione\": \"Descrizione\",\n  \"casaEditrice\": \"Editore3\",\n  \"genere\": \"Genere\",\n  \"posizione\": \"25\",\n  \"ripiano\": \"5\",\n  \"lingua\": \"Italiano\",\n  \"idAutore\": [\n    11\n  ]\n}";
    String id = "2444";

    @Test
    public void testUpdateErr() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/books/update").contentType(MediaType.APPLICATION_JSON)
            .content(jsonUpdateErr)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.codice").value(404))
            .andExpect(jsonPath("$.messaggio").value("Il libro con codice "+ id + " non e stato trovato!"))
            .andDo(print());

    }

    @Disabled
    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/delete/19")
            .contentType(MediaType.APPLICATION_JSON))   
            .andExpect(status().isOk())
            .andDo(print());
    }


    @Test
    public void testDeleteErr() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/books/delete/" + id)
            .contentType(MediaType.APPLICATION_JSON))   
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.codice").value(404))
            .andExpect(jsonPath("$.messaggio").value("Il libro con codice "+ id + " non e stato trovato!"))
            .andDo(print());
    }


}
