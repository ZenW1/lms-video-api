package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.CourseRequest;
import co.springcore.learning.dto.response.CourseResponse;
import co.springcore.learning.entity.CategoryEntity;
import co.springcore.learning.entity.CourseEntity;
import co.springcore.learning.entity.InstructorProfileEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.CategoryRepository;
import co.springcore.learning.repository.CourseRepository;
import co.springcore.learning.repository.InstructionProfileRepository;
import co.springcore.learning.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final InstructionProfileRepository instructorRepository;

    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAll()
                .stream()
                .filter(c -> !c.getIsDeleted())
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CourseResponse findById(Integer id) {
        CourseEntity course = courseRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Course not found with id: " + id));
        return toResponse(course);
    }

    @Override
    public CourseResponse create(CourseRequest request) {
        CategoryEntity category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .filter(c -> !c.getIsDeleted())
                    .orElseThrow(() -> new GlobalException("Category not found with id: " + request.categoryId()));
        }

        InstructorProfileEntity instructor = null;
        if (request.instructorId() != null) {
            try {
                Long instructorIdLong = Long.parseLong(request.instructorId());
                instructor = instructorRepository.findById(instructorIdLong)
                        .orElseThrow(
                                () -> new GlobalException("Instructor not found with id: " + request.instructorId()));
            } catch (NumberFormatException e) {
                throw new GlobalException("Invalid Instructor ID format: " + request.instructorId());
            }
        }

        CourseEntity course = new CourseEntity();
        course.setTitle(request.title());
        course.setSlug(request.slug());
        course.setDescription(request.description());
        course.setThumbnail(request.thumbnail());
        course.setPrice(request.price());
        course.setDiscountPercent(request.discountPercent() != null ? request.discountPercent() : 0.0f);
        course.setLevel(request.level());
        course.setKeyword(request.keyword());
        course.setTotalHours(request.totalHours());

        course.setCategory(category);
        course.setInstructor(instructor);

        course.setCountRating(0);
        course.setStarRating(0.0f);
        course.setIsDeleted(false);
        course.setIsPublished(false);
        course.setCreatedAt(Instant.now());
        course.setUpdatedAt(Instant.now());

        course.setCreatedBy("system");
        course.setLastModifiedBy("system");

        CourseEntity saved = courseRepository.save(course);
        return toResponse(saved);
    }

    @Override
    public CourseResponse update(Integer id, CourseRequest request) {
        CourseEntity course = courseRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Course not found with id: " + id));

        CategoryEntity category = null;
        if (request.categoryId() != null) {
            category = categoryRepository.findById(request.categoryId())
                    .filter(c -> !c.getIsDeleted())
                    .orElseThrow(() -> new GlobalException("Category not found with id: " + request.categoryId()));
        }

        InstructorProfileEntity instructor = null;
        if (request.instructorId() != null) {
            try {
                Long instructorIdLong = Long.parseLong(request.instructorId());
                instructor = instructorRepository.findById(instructorIdLong)
                        .orElseThrow(
                                () -> new GlobalException("Instructor not found with id: " + request.instructorId()));
            } catch (NumberFormatException e) {
                throw new GlobalException("Invalid Instructor ID format: " + request.instructorId());
            }
        }

        course.setTitle(request.title());
        course.setSlug(request.slug());
        course.setDescription(request.description());
        course.setThumbnail(request.thumbnail());
        course.setPrice(request.price());
        if (request.discountPercent() != null) {
            course.setDiscountPercent(request.discountPercent());
        }
        course.setLevel(request.level());
        course.setKeyword(request.keyword());
        course.setTotalHours(request.totalHours());

        course.setCategory(category);
        course.setInstructor(instructor);

        course.setUpdatedAt(Instant.now());
        course.setLastModifiedAt(Instant.now());
        course.setLastModifiedBy("system");

        CourseEntity updated = courseRepository.save(course);
        return toResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        CourseEntity course = courseRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Course not found with id: " + id));

        course.setIsDeleted(true);
        course.setLastModifiedAt(Instant.now());
        courseRepository.save(course);
    }

    private CourseResponse toResponse(CourseEntity entity) {
        return new CourseResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getSlug(),
                entity.getDescription(),
                entity.getThumbnail(),
                entity.getPrice(),
                entity.getDiscountPercent(),
                entity.getLevel(),
                entity.getKeyword(),
                entity.getTotalHours(),
                entity.getStarRating(),
                entity.getCountRating(),
                entity.getIsPublished(),
                entity.getCategory() != null ? entity.getCategory().getName() : null,
                entity.getInstructor() != null ? String.valueOf(entity.getInstructor().getUserId()) : null,
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
