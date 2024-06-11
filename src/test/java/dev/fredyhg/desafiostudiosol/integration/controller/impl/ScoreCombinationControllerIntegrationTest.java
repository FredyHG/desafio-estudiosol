package dev.fredyhg.desafiostudiosol.integration.controller.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoreCombinationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnValidCombinationsWhenVerifyEndpointIsCalled() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"score\":\"3x15\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.combinations").isNotEmpty());
    }

    @Test
    void shouldReturnZeroCombinationsWhenVerifyEndpointIsCalledWithInvalidScore() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.post("/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"score\":\"5x15\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.combinations").value("0"));
    }

    @Test
    void shouldReturnBadRequestWhenScoresAreEqual() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"score\":\"15x15\"}"))
                .andExpect(status().isBadRequest());
    }
}