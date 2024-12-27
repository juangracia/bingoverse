package com.bingoverse.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BingoCardService {

    @Autowired
    private ConfigService configService;

    public List<List<String>> generateBingoCards(String category, int count) {
        List<String> topics = configService.fetchTopics(category);

        return new Random().ints(0, topics.size())
            .distinct()
            .limit(count)
            .mapToObj(i -> topics.subList(i, Math.min(i + 5, topics.size())))
            .collect(Collectors.toList());
    }
}