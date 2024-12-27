package com.bingoverse.generator.controller;

import com.bingoverse.generator.model.BingoCard;
import com.bingoverse.generator.repository.BingoCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/bingo")
public class BingoController {
    @Autowired
    private BingoCardRepository repository;

    @PostMapping("/generate/{count}")
    public List<BingoCard> generateBingoCards(@PathVariable int count, @RequestBody List<String> topics) {
        List<BingoCard> cards = IntStream.range(0, count)
            .mapToObj(i -> {
                List<String> items = new Random().ints(0, topics.size())
                    .distinct().limit(5)
                    .mapToObj(topics::get)
                    .collect(Collectors.toList());
                BingoCard card = new BingoCard();
                card.setItems(items);
                return card;
            })
            .collect(Collectors.toList());
        return repository.saveAll(cards);
    }

    @GetMapping("/all")
    public List<BingoCard> getAllBingoCards() {
        return repository.findAll();
    }
}
