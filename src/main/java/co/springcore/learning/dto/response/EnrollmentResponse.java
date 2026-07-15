package co.springcore.learning.dto.response;

import java.time.Instant;

public record EnrollmentResponse(
        Integer id,
        Instant enrolledAt,
        String paymentMethod,
        Boolean paymentStatus,
        Instant paymentAt,
        Integer courseId,
        Long studentId,
        Instant createdAt
) {
}
