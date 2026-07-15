package co.springcore.learning.dto.request;

public record VideoRequest(
        String title,
        String slug,
        String thumbnail,
        String youtube,
        String duration,
        Integer courseId
) {
}
