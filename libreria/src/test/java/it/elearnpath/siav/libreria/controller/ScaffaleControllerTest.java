package it.elearnpath.siav.libreria.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
public class ScaffaleControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    
    private String baseUrl = "/scaffale";

    String jsonData = "{\r\n    \"id\": 1,\r\n    \"numero\": 1,\r\n    \"ripiano\": 1\r\n}";
    



    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getByIdTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/1")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andExpect(content().json(jsonData))
               .andReturn();
    }


    private Integer id = 10000;

    @Test
    public void getByIdErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/search/" + id)
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Non è presente alcun elemento con questo id " + id))
               .andDo(print());
               
    }


    @Test
    public void getAllTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
               .andReturn();

    }


    String jsonDataAdd = "{\r\n  \"numero\": 15,\r\n    \"ripiano\": 1\r\n}";
    
    @Test
    public void insertScaffaleTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataAdd)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated())
               .andDo(print());
    }


    @Test
    public void insertScaffaleErrTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonData)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotAcceptable())
               .andExpect(jsonPath("$.codice").value(406))
               .andExpect(jsonPath("$.messaggio").value("E' gia presente questo elemento"))
               .andDo(print());
    }

    String jsonDataUpdate = "{\r\n    \"id\": 2,\r\n    \"numero\": 2,\r\n    \"ripiano\": 2\r\n}";

    @Test
    public void updateScaffaleTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonDataUpdate)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(print());

    }


    String jsonDataUpdateErr = "{\r\n    \"id\": 2000000,\r\n    \"numero\": 2,\r\n    \"ripiano\": 2\r\n}";


    @Test
    public void updateElementErrTest2() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/update")
               .contentType(MediaType.APPLICATION_JSON)
               .content(jsonDataUpdateErr)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Scaffale non presente"))
               .andDo(print());

    }


    @Test
    public void deleteElement() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/delete/2")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andDo(print());

    }

    @Test
    public void deleteErrElement() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/delete/20000")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.codice").value(404))
               .andExpect(jsonPath("$.messaggio").value("Scaffale non presente"))
               .andDo(print());

    }
}