package aarexer.application.controller;

import aarexer.application.model.library.Book;
import aarexer.application.repository.library.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Long id) {
        Optional<Book> book = repository.getById(id);
        return book.<ResponseEntity<?>>map(b -> new ResponseEntity<>(b.getName(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(String.format("No book with id: %s", id), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Collection<String>> books() {
        List<String> bookCatalog = repository.findAll().stream().map(Book::getName).collect(Collectors.toList());
        return new ResponseEntity<>(bookCatalog, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        repository.removeById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
