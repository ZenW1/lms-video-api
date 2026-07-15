package co.springcore.learning.dto.request;

public record EnrollmentRequest(
        Integer courseId,
        Long studentId,
        String paymentMethod
) {
}
