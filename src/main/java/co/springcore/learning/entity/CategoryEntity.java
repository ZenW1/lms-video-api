package co.springcore.learning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String icon;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private Instant createdAt;

    @Column(length = 255)
    private String createdBy;

    @Column
    private Instant lastModifiedAt;

    @Column(length = 255)
    private String lastModifiedBy;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CourseEntity> courses;
}
