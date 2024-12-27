package com.bingoverse.config.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bingoverse.config.model.Topic;
import com.bingoverse.config.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TopicControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private TopicController topicController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
    }

    @Test
    public void testGetTopicsByCategory() throws Exception {
        // Mock example data for the test
        List<Topic> topics = List.of(
            new Topic(1L, "bands_80s", "Queen"),
            new Topic(2L, "bands_80s", "The Cure")
        );

        // Configure the repository mock to return the example data
        when(topicRepository.findByCategory("bands_80s")).thenReturn(topics);

        // Perform the GET request and validate the response
        mockMvc.perform(get("/topics/bands_80s"))
            .andExpect(status().isOk()) // Verify HTTP 200 response
            .andExpect(content().json("[\"Queen\", \"The Cure\"]")); // Verify JSON content
    }

    @Test
    public void testGetTopicsByCategoryNotFound() throws Exception {
        // Configure the repository mock to return an empty list
        when(topicRepository.findByCategory("unknown_category")).thenReturn(List.of());

        // Perform the GET request and validate the response
        mockMvc.perform(get("/topics/unknown_category"))
            .andExpect(status().isOk()) // Verify HTTP 200 response
            .andExpect(content().json("[]")); // Verify empty JSON array
    }
}
