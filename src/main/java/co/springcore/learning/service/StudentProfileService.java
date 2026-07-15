package co.springcore.learning.service;

import co.springcore.learning.dto.request.StudentProfileRequest;
import co.springcore.learning.dto.response.StudentProfileResponse;

import java.util.List;

public interface StudentProfileService {
    List<StudentProfileResponse> findAll();

    StudentProfileResponse findById(Long id);

    StudentProfileResponse create(StudentProfileRequest body);

    StudentProfileResponse update(Long id, StudentProfileRequest body);

    void delete(Long id);
}
