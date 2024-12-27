package com.bingoverse.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BingoCardServiceTest {

    private BingoCardService bingoCardService;

    @Mock
    private ConfigService configService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bingoCardService = new BingoCardService(configService); // Inject mock ConfigService
    }

    @Test
    public void testGenerateBingoCards() {
        String category = "bands_80s";
        List<String> mockTopics = List.of("Queen", "The Cure", "U2", "Depeche Mode", "Duran Duran");
        Mockito.when(configService.fetchTopics(category)).thenReturn(mockTopics);

        List<List<String>> bingoCards = bingoCardService.generateBingoCards(category, 3);

        assertEquals(3, bingoCards.size());
        bingoCards.forEach(card -> assertEquals(5, card.size()));
    }

    @Test
    public void testGenerateBingoCardsNotEnoughTopics() {
        String category = "bands_80s";
        List<String> mockTopics = List.of("Queen", "The Cure", "U2"); // Less than 5 topics
        Mockito.when(configService.fetchTopics(category)).thenReturn(mockTopics);

        assertThrows(IllegalArgumentException.class, () -> {
            bingoCardService.generateBingoCards(category, 3);
        });
    }
}
