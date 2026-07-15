package co.springcore.learning.dto.response;

import java.time.Instant;

public record InstructorProfileResponse(
        Long userId,
        String biography,
        String jobTitle,
        String phoneNumber,
        String facebookLink,
        String githubLink,
        Instant createdAt
) {
}
