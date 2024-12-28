package com.bingoverse.generator.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Represents a bingo card with a unique ID and a list of items.
 */
@Document(collection = "bingo_cards")
@Schema(description = "Represents a bingo card stored in the database")
@Data
public class BingoCard {

    @Id
    @Schema(description = "Unique ID of the bingo card", example = "12345")
    private String id;

    @Schema(description = "List of items on the bingo card", example = "[\"Queen\", \"U2\", \"The Cure\", \"Depeche Mode\", \"Duran Duran\"]")
    private List<String> items;
}
