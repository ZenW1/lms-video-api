package co.springcore.learning.service;

import co.springcore.learning.dto.request.CategoryRequest;
import co.springcore.learning.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();

    CategoryResponse findById(Integer id);

    CategoryResponse create(CategoryRequest request);

    CategoryResponse update(Integer id, CategoryRequest request);

    void delete(Integer id);
}
