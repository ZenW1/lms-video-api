package co.springcore.learning.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

public record CourseResponse(
        Integer id,
        String title,
        String slug,
        String description,
        String thumbnail,
        BigDecimal price,
        Float discountPercent,
        String level,
        String keyword,
        Float totalHours,
        Float starRating,
        Integer countRating,
        Boolean isPublished,
        String categoryName,
        String instructorId,
        Instant createdAt,
        Instant updatedAt
) {
}
