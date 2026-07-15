package co.springcore.learning.service.impl;

import co.springcore.learning.dto.request.CommentRequest;
import co.springcore.learning.dto.response.CommentResponse;
import co.springcore.learning.entity.CommentEntity;
import co.springcore.learning.entity.VideoEntity;
import co.springcore.learning.exception.GlobalException;
import co.springcore.learning.repository.CommentRepository;
import co.springcore.learning.repository.VideoRepository;
import co.springcore.learning.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final VideoRepository videoRepository;

    @Override
    public List<CommentResponse> findAll() {
        return commentRepository.findAll()
                .stream()
                .filter(c -> !c.getIsDeleted())
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CommentResponse findById(Integer id) {
        CommentEntity comment = commentRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Comment not found with id: " + id));
        return toResponse(comment);
    }

    @Override
    public CommentResponse create(CommentRequest request) {
//        VideoEntity video = videoRepository.findById(request.videoId()).filter(v -> !v.getIsDeleted()).orElseThrow(() -> new GlobalException("Video not found with id: " + request.videoId()));


//        CommentEntity parent = null;
//        if (request.parentId() != null) {
//            parent = commentRepository.findById(request.parentId())
//                    .filter(c -> !c.getIsDeleted())
//                    .orElseThrow(() -> new GlobalException("Parent comment not found with id: " + request.parentId()));
//        }

        CommentEntity comment = new CommentEntity();
        comment.setText(request.text());
//        comment.setVideo(video);
//        comment.setParent(parent);
        comment.setIsDeleted(false);
        comment.setCreatedAt(Instant.now());
        comment.setLastModifiedAt(Instant.now());

        comment.setCreatedBy("system");
        comment.setLastModifiedBy("system");

        CommentEntity saved = commentRepository.save(comment);
        return toResponse(saved);
    }

    @Override
    public CommentResponse update(Integer id, CommentRequest request) {
        CommentEntity comment = commentRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Comment not found with id: " + id));

        comment.setText(request.text());
        comment.setLastModifiedAt(Instant.now());

        CommentEntity updated = commentRepository.save(comment);
        return toResponse(updated);
    }

    @Override
    public void delete(Integer id) {
        CommentEntity comment = commentRepository.findById(id)
                .filter(c -> !c.getIsDeleted())
                .orElseThrow(() -> new GlobalException("Comment not found with id: " + id));

        comment.setIsDeleted(true);
        comment.setLastModifiedAt(Instant.now());
        commentRepository.save(comment);
    }

    private CommentResponse toResponse(CommentEntity entity) {
        List<CommentResponse> replies = entity.getReplies() != null ? entity.getReplies().stream().filter(r -> !r.getIsDeleted()).map(this::toResponse).toList() : Collections.emptyList();

        return new CommentResponse(
                entity.getId(),
                entity.getText(),
                entity.getCreatedBy(),
                entity.getVideo() != null ? entity.getVideo().getId() : null,
                entity.getParent() != null ? entity.getParent().getId() : null,
                replies,
                entity.getCreatedAt(),
                entity.getLastModifiedAt()
        );
    }
}
