package co.springcore.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "instructor_profiles")
@NoArgsConstructor

public class InstructorProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(length = 255)
    private String biography;

    @Column(length = 255)
    private String facebookLink;

    @Column(length = 255)
    private String githubLink;

    @Column(length = 255)
    private String jobTitle;

    @Column(length = 255)
    private String phoneNumber;

    @Column
    private Instant createdAt;

    @Column(length = 255)
    private String createdBy;

    @Column
    private Instant lastModifiedAt;

    @Column(length = 255)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<CourseEntity> courses;
}
