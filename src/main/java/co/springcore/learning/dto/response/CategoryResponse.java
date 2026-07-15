package co.springcore.learning.dto.response;

import java.time.Instant;

public record CategoryResponse(
        Integer id,
        String name,
        String icon,
        Instant createdAt
) {
}
