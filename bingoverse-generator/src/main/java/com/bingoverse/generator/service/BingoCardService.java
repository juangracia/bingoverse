package com.bingoverse.generator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BingoCardService {

    private final ConfigService configService;

    public BingoCardService(ConfigService configService) {
        this.configService = configService;
    }

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
}
