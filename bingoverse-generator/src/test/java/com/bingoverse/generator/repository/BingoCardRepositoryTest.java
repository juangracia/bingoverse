package com.bingoverse.generator.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bingoverse.generator.model.BingoCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

@DataMongoTest
public class BingoCardRepositoryTest {

    @Autowired
    private BingoCardRepository repository;

    @Test
    public void testSaveAndRetrieve() {
        BingoCard card = new BingoCard();
        card.setItems(List.of("Queen", "U2", "The Cure", "Depeche Mode", "Duran Duran"));

        repository.save(card);

        List<BingoCard> cards = repository.findAll();
        assertEquals(1, cards.size());
        assertEquals(5, cards.get(0).getItems().size());
    }
}
