package co.springcore.learning.controller;

import co.springcore.learning.dto.request.EnrollmentRequest;
import co.springcore.learning.dto.response.EnrollmentResponse;
import co.springcore.learning.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> findAll() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(enrollmentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> create(@RequestBody EnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponse> update(@PathVariable Integer id,
                                                     @RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        enrollmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
