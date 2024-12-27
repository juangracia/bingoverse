package com.bingoverse.config.repository;

import com.bingoverse.config.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCategory(String category);
}
