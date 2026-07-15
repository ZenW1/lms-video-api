package co.springcore.learning.controller;

import co.springcore.learning.dto.request.StudentProfileRequest;
import co.springcore.learning.dto.response.StudentProfileResponse;
import co.springcore.learning.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    @GetMapping
    public ResponseEntity<List<StudentProfileResponse>> findAll() {
        return ResponseEntity.ok(studentProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(studentProfileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StudentProfileResponse> create(@RequestBody StudentProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentProfileService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> update(@PathVariable Long id,
                                                         @RequestBody StudentProfileRequest request) {
        return ResponseEntity.ok(studentProfileService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
