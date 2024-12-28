// src/test/java/com/bingoverse/generator/controller/BingoControllerTest.java
package com.bingoverse.generator.controller;

import com.bingoverse.generator.model.BingoCard;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public void testGetAllCards() throws Exception {
        // Mocked response
        List<BingoCard> mockCards = List.of(new BingoCard(), new BingoCard());

        // Setting up mock behavior
        Mockito.when(bingoCardService.getAllCards()).thenReturn(mockCards);

        // Performing the request and validating the response
        mockMvc.perform(get("/bingo/all")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testSaveCard() throws Exception {
        // Mocked request and response
        BingoCard card = new BingoCard();
        card.setItems(List.of("Queen", "U2", "The Cure", "Depeche Mode", "Duran Duran"));
        Mockito.when(bingoCardService.saveCard(Mockito.any(BingoCard.class))).thenReturn(card);

        // Performing the request and validating the response
        mockMvc.perform(post("/bingo/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"items\": [\"Queen\", \"U2\", \"The Cure\", \"Depeche Mode\", \"Duran Duran\"]}")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.items.length()").value(5));
    }
}