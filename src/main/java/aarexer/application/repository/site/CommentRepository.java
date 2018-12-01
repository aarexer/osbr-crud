package aarexer.application.repository.site;

import aarexer.application.model.site.Comment;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CommentRepository extends LongKeyedRepository<Comment> {
}
