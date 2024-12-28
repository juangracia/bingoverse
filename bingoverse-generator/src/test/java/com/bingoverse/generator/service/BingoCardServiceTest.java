// src/test/java/com/bingoverse/generator/service/BingoCardServiceTest.java
package com.bingoverse.generator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.bingoverse.generator.model.BingoCard;
import com.bingoverse.generator.repository.BingoCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class BingoCardServiceTest {

    private BingoCardService bingoCardService;

    @Mock
    private ConfigService configService;

    @Mock
    private BingoCardRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bingoCardService = new BingoCardService(configService, repository);
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

    @Test
    public void testSaveCard() {
        BingoCard card = new BingoCard();
        card.setItems(List.of("Queen", "U2", "The Cure", "Depeche Mode", "Duran Duran"));
        Mockito.when(repository.save(card)).thenReturn(card);

        BingoCard savedCard = bingoCardService.saveCard(card);

        assertEquals(card, savedCard);
    }

    @Test
    public void testGetAllCards() {
        List<BingoCard> mockCards = List.of(new BingoCard(), new BingoCard());
        Mockito.when(repository.findAll()).thenReturn(mockCards);

        List<BingoCard> cards = bingoCardService.getAllCards();

        assertEquals(mockCards, cards);
    }
}