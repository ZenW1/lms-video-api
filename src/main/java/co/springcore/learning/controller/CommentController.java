package co.springcore.learning.controller;

import co.springcore.learning.dto.request.CommentRequest;
import co.springcore.learning.dto.response.CommentResponse;
import co.springcore.learning.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponse>> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody CommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Integer id,
                                                  @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
