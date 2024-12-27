package com.bingoverse.generator.service;

import com.bingoverse.generator.model.BingoCard;
import com.bingoverse.generator.repository.BingoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service class responsible for generating bingo cards based on topics fetched from the ConfigService.
 */
@Service
public class BingoCardService {

    private final ConfigService configService;

    @Autowired
    private BingoCardRepository repository;

    /**
     * Constructor to initialize BingoCardService with ConfigService dependency.
     *
     * @param configService the service used to fetch topics for generating bingo cards.
     */
    public BingoCardService(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * Generates a specified number of bingo cards, each containing exactly 5 unique topics.
     *
     * @param category the category of topics to fetch from the ConfigService.
     * @param count    the number of bingo cards to generate.
     * @return a list of bingo cards, where each card is a list of 5 unique topics.
     * @throws IllegalArgumentException if there are fewer than 5 topics available in the specified category.
     */
    public List<List<String>> generateBingoCards(String category, int count) {
        List<String> topics = new ArrayList<>(configService.fetchTopics(category)); // Create a mutable copy
        if (topics.size() < 5) {
            throw new IllegalArgumentException("Not enough topics to generate a bingo card.");
        }

        List<List<String>> bingoCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Collections.shuffle(topics); // Randomize the mutable list
            bingoCards.add(new ArrayList<>(topics.subList(0, 5))); // Take the first 5 items
        }
        return bingoCards;
    }

    public BingoCard saveCard(BingoCard card) {
        return repository.save(card);
    }

    public List<BingoCard> getAllCards() {
        return repository.findAll();
    }
}
