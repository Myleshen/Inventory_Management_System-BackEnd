package dev.myleshenp.ims.dto;

import java.time.LocalDateTime;

public record CategoryDTO (
        Long id,
        String name,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
){
}
