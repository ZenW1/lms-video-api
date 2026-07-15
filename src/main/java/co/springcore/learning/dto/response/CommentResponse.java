package co.springcore.learning.dto.response;

import java.time.Instant;
import java.util.List;

public record CommentResponse(
        Integer id,
        String text,
        String createdBy,
        Integer videoId,
        Integer parentId,
        List<CommentResponse> replies,
        Instant createdAt,
        Instant lastModifiedAt
) {
}
