package com.bingoverse.generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConfigService {

    @Value("${bingoverse.config.base-url}")
    private String configBaseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> fetchTopics(String category) {
        String url = configBaseUrl + "/topics/" + category;
        return restTemplate.getForObject(url, List.class);
    }
}
