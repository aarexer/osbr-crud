package aarexer.application.controller;

import aarexer.application.model.library.Author;
import aarexer.application.repository.library.AuthorRepository;
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
@RequestMapping("/api/author")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorService) {
        this.authorRepository = authorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> authorById(@PathVariable Long id) {
        Optional<Author> author = authorRepository.getById(id);
        return author.<ResponseEntity<?>>map(g -> new ResponseEntity<>(g.getName(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(String.format("No author with id: %s", id), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Collection<String>> books() {
        List<String> authors = authorRepository.findAll().stream().map(Author::getName).collect(Collectors.toList());
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        authorRepository.removeById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
