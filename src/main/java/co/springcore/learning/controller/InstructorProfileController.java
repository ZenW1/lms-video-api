package co.springcore.learning.controller;

import co.springcore.learning.dto.request.InstructorProfileRequest;
import co.springcore.learning.dto.response.InstructorProfileResponse;
import co.springcore.learning.service.InstructorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorProfileController {

    private final InstructorProfileService instructorProfileService;

    @GetMapping
    public ResponseEntity<List<InstructorProfileResponse>> findAll() {
        return ResponseEntity.ok(instructorProfileService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorProfileResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(instructorProfileService.findById(id));
    }

    @PostMapping
    public ResponseEntity<InstructorProfileResponse> create(@RequestBody InstructorProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorProfileService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstructorProfileResponse> update(@PathVariable Long id,
                                                            @RequestBody InstructorProfileRequest request) {
        return ResponseEntity.ok(instructorProfileService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorProfileService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
