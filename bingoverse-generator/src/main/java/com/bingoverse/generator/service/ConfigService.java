
package com.bingoverse.generator.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * Service class responsible for fetching topic data from a remote configuration service.
 */
@Service
public class ConfigService {

    @Value("${bingoverse.config.base-url}")
    private String configBaseUrl;

    private final RestTemplate restTemplate;

    /**
     * Constructor to initialize ConfigService with a RestTemplate instance.
     *
     * @param restTemplate the RestTemplate used for making HTTP requests.
     */
    public ConfigService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Updates the base URL for the configuration service.
     *
     * @param configBaseUrl the base URL for the configuration service.
     */
    public void setConfigBaseUrl(String configBaseUrl) {
        this.configBaseUrl = configBaseUrl;
    }

    /**
     * Fetches a list of topics from the remote configuration service for a given category.
     *
     * @param category the category of topics to fetch.
     * @return a list of topics corresponding to the specified category.
     * @throws IllegalArgumentException if the URL for the request is not valid.
     */
    public List<String> fetchTopics(String category) {
        String url = configBaseUrl + "/topics/" + category;
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            throw new IllegalArgumentException("The URL must be absolute: " + url);
        }
        return restTemplate.getForObject(url, List.class);
    }
}
