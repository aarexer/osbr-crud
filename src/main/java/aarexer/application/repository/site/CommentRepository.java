package aarexer.application.repository.site;

import aarexer.application.model.site.Comment;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends LongKeyedRepository<Comment> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
