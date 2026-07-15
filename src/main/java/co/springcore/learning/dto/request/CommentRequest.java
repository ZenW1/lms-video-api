package co.springcore.learning.dto.request;

public record CommentRequest(
        String text,
        Integer videoId,
        Integer parentId
) {
}
