package co.springcore.learning.dto.response;

import java.time.Instant;

public record StudentProfileResponse(
        Long userId,
        String university,
        String major,
        String phoneNumber,
        String facebookLink,
        String githubLink,
        String profilePicture,
        Instant createdAt
) {
}
