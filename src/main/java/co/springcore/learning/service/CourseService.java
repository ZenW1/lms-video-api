package co.springcore.learning.service;

import co.springcore.learning.dto.request.CourseRequest;
import co.springcore.learning.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    List<CourseResponse> findAll();

    CourseResponse findById(Integer id);

    CourseResponse create(CourseRequest body);

    CourseResponse update(Integer id, CourseRequest body);

    void delete(Integer id);
}
