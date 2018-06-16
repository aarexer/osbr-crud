package aarexer.application.repository.site;

import aarexer.application.model.site.Tag;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends LongKeyedRepository<Tag> {
}
