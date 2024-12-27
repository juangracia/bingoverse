package com.bingoverse.generator.controller;

import com.bingoverse.generator.service.BingoCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BingoControllerTest {

    private MockMvc mockMvc;
    private BingoCardService bingoCardService;

    @BeforeEach
    public void setup() {
        // Mocking the BingoCardService
        bingoCardService = mock(BingoCardService.class);

        // Injecting the mocked service into the controller
        BingoController bingoController = new BingoController(bingoCardService);

        // Building MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(bingoController).build();
    }

    @Test
    public void testGenerateBingoCards() throws Exception {
        // Mocked response
        String category = "bands_80s";
        int count = 3;
        List<List<String>> mockBingoCards = List.of(
            List.of("Queen", "The Cure", "U2", "Depeche Mode", "Duran Duran"),
            List.of("U2", "Queen", "The Cure", "Depeche Mode", "Duran Duran"),
            List.of("Depeche Mode", "Queen", "The Cure", "U2", "Duran Duran")
        );

        // Setting up mock behavior
        Mockito.when(bingoCardService.generateBingoCards(category, count)).thenReturn(mockBingoCards);

        // Performing the request and validating the response
        mockMvc.perform(get("/bingo/{category}/{count}", category, count)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(3));
    }
}
