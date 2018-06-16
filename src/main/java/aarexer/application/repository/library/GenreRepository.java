package aarexer.application.repository.library;

import aarexer.application.model.library.Genre;
import aarexer.application.repository.KeyedRepository;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends LongKeyedRepository<Genre> {
}
