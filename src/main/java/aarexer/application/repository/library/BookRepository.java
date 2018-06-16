package aarexer.application.repository.library;

import aarexer.application.model.library.Book;
import aarexer.application.repository.LongKeyedRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends LongKeyedRepository<Book> {
    List<Book> findByNameLike(String name);
}
