package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.InstructorProfileRequest;
import co.springcore.learning.dto.response.InstructorProfileResponse;
import co.springcore.learning.entity.InstructorProfileEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.InstructionProfileRepository;
import co.springcore.learning.service.InstructorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorProfileServiceImpl implements InstructorProfileService {

    private final InstructionProfileRepository instructorRepository;

    @Override
    public List<InstructorProfileResponse> findAll() {
        return instructorRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public InstructorProfileResponse findById(Long id) {
        InstructorProfileEntity instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Instructor not found with id: " + id));
        return toResponse(instructor);
    }

    @Override
    public InstructorProfileResponse create(InstructorProfileRequest request) {
        InstructorProfileEntity instructor = new InstructorProfileEntity();
        instructor.setBiography(request.biography());
        instructor.setJobTitle(request.jobTitle());
        instructor.setPhoneNumber(request.phoneNumber());
        instructor.setFacebookLink(request.facebookLink());
        instructor.setGithubLink(request.githubLink());
        instructor.setCreatedAt(Instant.now());
        instructor.setLastModifiedAt(Instant.now());

        // normally set via security context
        instructor.setCreatedBy("system");
        instructor.setLastModifiedBy("system");

        InstructorProfileEntity saved = instructorRepository.save(instructor);
        return toResponse(saved);
    }

    @Override
    public InstructorProfileResponse update(Long id, InstructorProfileRequest request) {
        InstructorProfileEntity instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Instructor not found with id: " + id));

        instructor.setBiography(request.biography());
        instructor.setJobTitle(request.jobTitle());
        instructor.setPhoneNumber(request.phoneNumber());
        instructor.setFacebookLink(request.facebookLink());
        instructor.setGithubLink(request.githubLink());
        instructor.setLastModifiedAt(Instant.now());
        instructor.setLastModifiedBy("system");

        InstructorProfileEntity updated = instructorRepository.save(instructor);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        InstructorProfileEntity instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Instructor not found with id: " + id));

        instructorRepository.delete(instructor);
    }

    private InstructorProfileResponse toResponse(InstructorProfileEntity entity) {
        return new InstructorProfileResponse(
                entity.getUserId(),
                entity.getBiography(),
                entity.getJobTitle(),
                entity.getPhoneNumber(),
                entity.getFacebookLink(),
                entity.getGithubLink(),
                entity.getCreatedAt());
    }
}
