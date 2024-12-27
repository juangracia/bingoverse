package com.bingoverse.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

public class ConfigServiceTest {

    @InjectMocks
    private ConfigService configService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Mocking the base URL
        configService = new ConfigService(restTemplate);
        configService.setConfigBaseUrl("http://localhost:8081");
    }

    @Test
    public void testFetchTopics() {
        String category = "bands_80s";
        String expectedUrl = "http://localhost:8081/topics/" + category;
        List<String> mockResponse = List.of("Queen", "The Cure", "U2", "Depeche Mode", "Duran Duran");

        // Mocking the RestTemplate response
        Mockito.when(restTemplate.getForObject(eq(expectedUrl), eq(List.class))).thenReturn(mockResponse);

        List<String> topics = configService.fetchTopics(category);

        // Validating the response
        assertEquals(mockResponse, topics);
    }
}
