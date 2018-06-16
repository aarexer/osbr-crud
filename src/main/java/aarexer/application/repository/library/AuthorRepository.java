package aarexer.application.repository.library;

import aarexer.application.model.library.Author;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends LongKeyedRepository<Author> {
}
