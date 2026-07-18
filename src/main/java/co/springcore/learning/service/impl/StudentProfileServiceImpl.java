package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.StudentProfileRequest;
import co.springcore.learning.dto.response.StudentProfileResponse;
import co.springcore.learning.entity.StudentProfileEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.StudentRepository;
import co.springcore.learning.service.StudentProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentProfileResponse> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public StudentProfileResponse findById(Long id) {
        StudentProfileEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Student not found with id: " + id));
        return toResponse(student);
    }

    @Override
    public StudentProfileResponse create(StudentProfileRequest request) {
        StudentProfileEntity student = new StudentProfileEntity();
        student.setUniversity(request.university());
        student.setMajor(request.major());
        student.setPhoneNumber(request.phoneNumber());
        student.setFacebookLink(request.facebookLink());
        student.setGithubLink(request.githubLink());
        student.setProfilePicture(request.profilePicture());
        student.setCreatedAt(Instant.now());
        student.setLastModifiedAt(Instant.now());

        // normally set via security context
        student.setCreatedBy("system");
        student.setLastModifiedBy("system");

        StudentProfileEntity saved = studentRepository.save(student);
        return toResponse(saved);
    }

    @Override
    public StudentProfileResponse update(Long id, StudentProfileRequest request) {
        StudentProfileEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Student not found with id: " + id));

        student.setUniversity(request.university());
        student.setMajor(request.major());
        student.setPhoneNumber(request.phoneNumber());
        student.setFacebookLink(request.facebookLink());
        student.setGithubLink(request.githubLink());
        student.setProfilePicture(request.profilePicture());
        student.setLastModifiedAt(Instant.now());
        student.setLastModifiedBy("system");

        StudentProfileEntity updated = studentRepository.save(student);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        StudentProfileEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Student not found with id: " + id));
        
        studentRepository.delete(student);
    }

    private StudentProfileResponse toResponse(StudentProfileEntity entity) {
        return new StudentProfileResponse(
                entity.getUserId(),
                entity.getUniversity(),
                entity.getMajor(),
                entity.getPhoneNumber(),
                entity.getFacebookLink(),
                entity.getGithubLink(),
                entity.getProfilePicture(),
                entity.getCreatedAt()
        );
    }
}
