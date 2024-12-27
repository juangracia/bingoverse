
package com.bingoverse.generator.controller;

import com.bingoverse.generator.service.BingoCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * REST controller for managing bingo card generation requests.
 */
@RestController
@RequestMapping("/bingo")
@RequiredArgsConstructor
public class BingoController {

    private final BingoCardService bingoCardService;

    /**
     * Endpoint to generate a specified number of bingo cards based on a topic category.
     *
     * @param category the category of topics to use for generating bingo cards.
     * @param count the number of bingo cards to generate.
     * @return a list of generated bingo cards.
     */
    @GetMapping("/{category}/{count}")
    public List<List<String>> generateBingoCards(@PathVariable String category, @PathVariable int count) {
        return bingoCardService.generateBingoCards(category, count);
    }
}
