package co.springcore.learning.dto.request;

public record StudentProfileRequest(
        String university,
        String major,
        String phoneNumber,
        String facebookLink,
        String githubLink,
        String profilePicture
) {
}
