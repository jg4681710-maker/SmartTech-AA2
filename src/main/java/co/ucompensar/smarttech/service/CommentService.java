package co.ucompensar.smarttech.service;

import co.ucompensar.smarttech.entity.Comment;
import co.ucompensar.smarttech.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> findByDevice(Long deviceId) {
        return repository.findByDeviceIdOrderByCreatedAtDesc(deviceId);
    }

    public Comment save(Comment comment) { return repository.save(comment); }
    public void delete(Long id) { repository.deleteById(id); }
}
