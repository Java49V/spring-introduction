package telran.spring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilder;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import telran.spring.service.GreetingsService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GreetingsControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private GreetingsService greetingsService;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new GreetingsController(greetingsService)).build();
    }

    @Test
    void addPersonWithWrongEmail() throws Exception {
        // Assume we have a JSON string for a person with wrong email format
        String personJson = "{\"id\":1,\"name\":\"John\",\"city\":\"City\",\"email\":\"wrongemail\",\"phone\":\"+972-123-4567890\"}";
        mockMvc.perform(post("/greetings/person")
                .contentType("application/json")
                .content(personJson))
                .andExpect(status().isBadRequest());
    }

    // Similar tests for wrong name, empty city, etc.

    @Test
    void updatePersonNormalFlow() throws Exception {
        // Assume valid JSON string for updating a person
        String personJson = "{\"id\":1,\"name\":\"John\",\"city\":\"City\",\"email\":\"john@example.com\",\"phone\":\"+972-123-4567890\"}";
        mockMvc.perform(put("/greetings/person")
                .contentType("application/json")
                .content(personJson))
                .andExpect(status().isOk());
    }

    @Test
    void getPerson() throws Exception {
        mockMvc.perform(get("/greetings/person/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/greetings/person/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getGreetings() throws Exception {
        mockMvc.perform(get("/greetings/1"))
                .andExpect(status().isOk());
    }

}
