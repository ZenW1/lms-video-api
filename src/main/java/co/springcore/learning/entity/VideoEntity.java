package co.springcore.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "videos")
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String duration;

    @Column
    private Boolean isDeleted = false;

    @Column
    private Boolean isPublished = false;

    @Column(length = 255)
    private String slug;

    @Column(length = 255)
    private String thumbnail;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String youtube;

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

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<CommentEntity> comments;
}
