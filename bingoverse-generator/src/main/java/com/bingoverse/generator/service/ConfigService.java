package com.bingoverse.generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ConfigService {

    @Value("${bingoverse.config.base-url}")
    private String configBaseUrl;

    private final RestTemplate restTemplate;

    public ConfigService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void setConfigBaseUrl(String configBaseUrl) {
        this.configBaseUrl = configBaseUrl;
    }

    public List<String> fetchTopics(String category) {
        String url = configBaseUrl + "/topics/" + category;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("The URL must be absolute: " + url);
        }
        return restTemplate.getForObject(url, List.class);
    }
}
