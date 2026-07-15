package co.springcore.learning.service;

import co.springcore.learning.dto.request.CommentRequest;
import co.springcore.learning.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> findAll();

    CommentResponse findById(Integer id);

    CommentResponse create(CommentRequest request);

    CommentResponse update(Integer id, CommentRequest request);

    void delete(Integer id);
}
