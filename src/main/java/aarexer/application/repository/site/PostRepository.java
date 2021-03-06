package aarexer.application.repository.site;

import aarexer.application.model.site.Post;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends LongKeyedRepository<Post> {
}
