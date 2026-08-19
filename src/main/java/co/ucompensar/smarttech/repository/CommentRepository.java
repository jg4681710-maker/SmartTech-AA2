package co.ucompensar.smarttech.repository;

import co.ucompensar.smarttech.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByDeviceIdOrderByCreatedAtDesc(Long deviceId);
}
