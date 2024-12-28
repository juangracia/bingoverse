package com.bingoverse.generator.controller;

import com.bingoverse.generator.model.BingoCard;
import com.bingoverse.generator.service.BingoCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for handling bingo card generation requests.
 */
@RestController
@RequestMapping("/bingo")
@RequiredArgsConstructor
@Tag(name = "Bingo API", description = "Endpoints for generating and retrieving bingo cards")
public class BingoController {

    private final BingoCardService bingoCardService;

    /**
     * Generates a specified number of bingo cards using topics from the given category.
     *
     * @param category the category of topics to generate bingo cards from
     * @param count    the number of bingo cards to generate
     * @return a list of bingo cards, each containing a set of topics
     */
    @GetMapping("/{category}/{count}")
    @Operation(
        summary = "Generate bingo cards",
        description = "Generates a specified number of bingo cards using topics from the given category."
    )
    public List<List<String>> generateBingoCards(
        @Parameter(description = "Category of topics to generate bingo cards from", example = "bands_80s")
        @PathVariable String category,
        @Parameter(description = "Number of bingo cards to generate", example = "3")
        @PathVariable int count) {
        return bingoCardService.generateBingoCards(category, count);
    }

    @GetMapping("/all")
    @Operation(summary = "Retrieve all bingo cards", description = "Fetches all saved bingo cards from the database.")
    public List<BingoCard> getAllCards() {
        return bingoCardService.getAllCards();
    }

    @PostMapping("/save")
    @Operation(summary = "Save a bingo card", description = "Saves a bingo card to the database.")
    public BingoCard saveCard(@RequestBody BingoCard card) {
        return bingoCardService.saveCard(card);
    }
}