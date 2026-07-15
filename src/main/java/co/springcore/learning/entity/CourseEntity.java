package co.springcore.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer countRating;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = true , length = 255)
    private String description;

    @Column(nullable = false)
    private Float discountPercent;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false)
    private Boolean isPublished = false;

    @Column(length = 255)
    private String keyword;

    @Column(length = 255)
    private String level;

    @Column(nullable = false,precision = 38, scale = 2)
    private BigDecimal price;

    @Column(length = 255)
    private String slug;

    @Column()
    private Float starRating;

    @Column(length = 255)
    private String thumbnail;

    @Column(length = 255)
    private String title;

    @Column
    private Float totalHours;

    @Column
    private Instant updatedAt;

    @Column(length = 255)
    private String createdBy;

    @Column
    private Instant lastModifiedAt;

    @Column(length = 255)
    private String lastModifiedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "user_id")
    private InstructorProfileEntity instructor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<EnrollmentEntity> enrollments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<VideoEntity> videos;
}
