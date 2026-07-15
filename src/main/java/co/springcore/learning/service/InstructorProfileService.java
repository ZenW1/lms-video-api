package co.springcore.learning.service;

import co.springcore.learning.dto.request.InstructorProfileRequest;
import co.springcore.learning.dto.response.InstructorProfileResponse;

import java.util.List;

public interface InstructorProfileService {
    List<InstructorProfileResponse> findAll();

    InstructorProfileResponse findById(Long id);

    InstructorProfileResponse create(InstructorProfileRequest body);

    InstructorProfileResponse update(Long id, InstructorProfileRequest body);

    void delete(Long id);

}
