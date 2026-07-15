package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.CategoryRequest;
import co.springcore.learning.dto.response.CategoryResponse;
import co.springcore.learning.entity.CategoryEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.CategoryRepository;
import co.springcore.learning.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .filter(c -> !c.getIsDeleted())
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse findById(Integer id) {
        CategoryEntity category = categoryRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Category not found with id: " + id));
        return toResponse(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        if (categoryRepository.existsByName(request.name())) {
            throw new GlobalException("Category with name '" + request.name() + "' already exists");
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(request.name());
        category.setIcon(request.icon());
        category.setIsDeleted(false);
        category.setCreatedAt(Instant.now());

        CategoryEntity saved = categoryRepository.save(category);
        return toResponse(saved);
    }

    @Override
    public CategoryResponse update(Integer id, CategoryRequest request) {
        CategoryEntity category = categoryRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Category not found with id: " + id));

        category.setName(request.name());
        category.setIcon(request.icon());
        category.setLastModifiedAt(Instant.now());

        CategoryEntity updated = categoryRepository.save(category);
        return toResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        CategoryEntity category = categoryRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Category not found with id: " + id));

        category.setIsDeleted(true);
        category.setLastModifiedAt(Instant.now());
        categoryRepository.save(category);
    }

    private CategoryResponse toResponse(CategoryEntity entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getIcon(),
                entity.getCreatedAt()
        );
    }
}
