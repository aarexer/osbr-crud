package aarexer.application.repository.site;

import aarexer.application.model.site.Tag;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TagRepository extends LongKeyedRepository<Tag> {
}
