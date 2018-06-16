package aarexer.application.controller;

import aarexer.application.model.library.Genre;
import aarexer.application.repository.library.GenreRepository;
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
@RequestMapping("/api/genre")
public class GenreController {
    private final GenreRepository repository;

    @Autowired
    public GenreController(GenreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> genreById(@PathVariable Long id) {
        Optional<Genre> genre = repository.getById(id);
        return genre.<ResponseEntity<?>>map(g -> new ResponseEntity<>(g.getName(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(String.format("No genre with id: %s", id), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<Collection<String>> books() {
        List<String> genreCatalog = repository.findAll().stream().map(Genre::getName).collect(Collectors.toList());
        return new ResponseEntity<>(genreCatalog, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        repository.removeById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
