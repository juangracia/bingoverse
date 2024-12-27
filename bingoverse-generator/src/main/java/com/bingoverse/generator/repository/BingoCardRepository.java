package com.bingoverse.generator.repository;

import com.bingoverse.generator.model.BingoCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BingoCardRepository extends MongoRepository<BingoCard, String> {
}
