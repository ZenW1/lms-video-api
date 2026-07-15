package co.springcore.learning.service;

import co.springcore.learning.dto.request.EnrollmentRequest;
import co.springcore.learning.dto.response.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    List<EnrollmentResponse> findAll();

    EnrollmentResponse findById(Integer id);

    EnrollmentResponse create(EnrollmentRequest body);

    EnrollmentResponse update(Integer id, EnrollmentRequest body);

    void delete(Integer id);

}
