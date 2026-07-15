package co.springcore.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "enrollments")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Instant enrolledAt;

    @Column
    private Instant paymentAt;

    @Column(length = 255)
    private String paymentMethod;

    @Column
    private Boolean paymentStatus;

    @Column
    private Instant createdAt;

    @Column(length = 255)
    private String createdBy;

    @Column
    private Instant lastModifiedAt;

    @Column(length = 255)
    private String lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "user_id")
    private StudentProfileEntity student;
}
