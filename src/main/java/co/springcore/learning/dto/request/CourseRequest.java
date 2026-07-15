package co.springcore.learning.dto.request;

import java.math.BigDecimal;

public record CourseRequest(
        String title,
        String slug,
        String description,
        String thumbnail,
        BigDecimal price,
        Float discountPercent,
        String level,
        String keyword,
        Float totalHours,
        Integer categoryId,
        String instructorId
) {
}
