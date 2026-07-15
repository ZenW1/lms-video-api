package co.springcore.learning.dto.request;

public record InstructorProfileRequest(
        String biography,
        String jobTitle,
        String phoneNumber,
        String facebookLink,
        String githubLink
) {
}
