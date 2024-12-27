package com.bingoverse.generator.controller;

import com.bingoverse.generator.service.BingoCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bingo")
public class BingoController {

    @Autowired
    private BingoCardService bingoCardService;

    @GetMapping("/{category}/{count}")
    public List<List<String>> generateBingoCards(@PathVariable String category, @PathVariable int count) {
        return bingoCardService.generateBingoCards(category, count);
    }
}
