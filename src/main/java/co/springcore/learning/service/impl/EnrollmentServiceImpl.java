package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.EnrollmentRequest;
import co.springcore.learning.dto.response.EnrollmentResponse;
import co.springcore.learning.entity.CourseEntity;
import co.springcore.learning.entity.EnrollmentEntity;
import co.springcore.learning.entity.StudentProfileEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.CourseRepository;
import co.springcore.learning.repository.EnrollmentRepository;
import co.springcore.learning.repository.StudentRepository;
import co.springcore.learning.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Override
    public List<EnrollmentResponse> findAll() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public EnrollmentResponse findById(Integer id) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Enrollment not found with id: " + id));
        return toResponse(enrollment);
    }

    @Override
    public EnrollmentResponse create(EnrollmentRequest request) {
        CourseEntity course = null;
        if (request.courseId() != null) {
            course = courseRepository.findById(request.courseId())
                    .filter(c -> !c.getIsDeleted())
                    .orElseThrow(() -> new GlobalException("Course not found with id: " + request.courseId()));
        }

        StudentProfileEntity student = null;
        if (request.studentId() != null) {
            student = studentRepository.findById(request.studentId())
                    .orElseThrow(() -> new GlobalException("Student not found with id: " + request.studentId()));
        }

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setPaymentMethod(request.paymentMethod());
        enrollment.setPaymentStatus(false);
        enrollment.setEnrolledAt(Instant.now());
        enrollment.setCreatedAt(Instant.now());
        enrollment.setLastModifiedAt(Instant.now());

        enrollment.setCreatedBy("system");
        enrollment.setLastModifiedBy("system");

        EnrollmentEntity saved = enrollmentRepository.save(enrollment);
        return toResponse(saved);
    }

    @Override
    public EnrollmentResponse update(Integer id, EnrollmentRequest request) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Enrollment not found with id: " + id));

        CourseEntity course = null;
        if (request.courseId() != null) {
            course = courseRepository.findById(request.courseId())
                    .filter(c -> !c.getIsDeleted())
                    .orElseThrow(() -> new GlobalException("Course not found with id: " + request.courseId()));
        }

        StudentProfileEntity student = null;
        if (request.studentId() != null) {
            student = studentRepository.findById(request.studentId())
                    .orElseThrow(() -> new GlobalException("Student not found with id: " + request.studentId()));
        }

        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollment.setPaymentMethod(request.paymentMethod());
        enrollment.setLastModifiedAt(Instant.now());
        enrollment.setLastModifiedBy("system");

        EnrollmentEntity updated = enrollmentRepository.save(enrollment);
        return toResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        EnrollmentEntity enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Enrollment not found with id: " + id));

        // Enrollment doesn't have an isDeleted flag in the entity, so we hard delete.
        enrollmentRepository.delete(enrollment);
    }

    private EnrollmentResponse toResponse(EnrollmentEntity entity) {
        return new EnrollmentResponse(
                entity.getId(),
                entity.getEnrolledAt(),
                entity.getPaymentMethod(),
                entity.getPaymentStatus(),
                entity.getPaymentAt(),
                entity.getCourse() != null ? entity.getCourse().getId() : null,
                entity.getStudent() != null ? entity.getStudent().getUserId() : null,
                entity.getCreatedAt());
    }
}
