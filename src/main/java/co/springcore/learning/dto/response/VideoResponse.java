package co.springcore.learning.dto.response;

import java.time.Instant;

public record VideoResponse(
        Integer id,
        String title,
        String slug,
        String thumbnail,
        String youtube,
        String duration,
        Boolean isPublished,
        Integer courseId,
        Instant createdAt
) {
}
