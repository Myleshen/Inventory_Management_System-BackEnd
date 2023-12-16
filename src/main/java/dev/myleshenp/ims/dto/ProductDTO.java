package dev.myleshenp.ims.dto;

import dev.myleshenp.ims.entity.Category;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDTO(
        Long id,
        String productCode,
        Double price,
        Double cgstPercentage,
        Double sgstPercentage,
        Long qty,
        List<CategoryDTO> categories,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
