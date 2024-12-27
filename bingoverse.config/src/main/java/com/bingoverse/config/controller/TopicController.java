package com.bingoverse.config.controller;

import com.bingoverse.config.model.Topic;
import com.bingoverse.config.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicRepository repository;

    @GetMapping("/{category}")
    public List<String> getTopics(@PathVariable String category) {
        return repository.findByCategory(category)
            .stream()
            .map(Topic::getName)
            .collect(Collectors.toList());
    }
}
