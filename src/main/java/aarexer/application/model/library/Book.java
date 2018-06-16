package aarexer.application.model.library;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "book")
@ToString(exclude = {"authors", "genres"})
@EqualsAndHashCode(exclude = {"genres", "authors"})
public class Book implements Serializable {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "publishing_house")
    private String publishingHouse;

    @NotNull
    @Column(name = "date_publishing")
    private LocalDate datePublishing;

    @NotNull
    @Column(name = "download_link")
    private String downloadLink;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "book_author_junction",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "book_genres_junction",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id", referencedColumnName = "id")})
    private Set<Genre> genres = new HashSet<>();

    public Book() {
    }

    public Book(String name, String description, String publishingHouse, LocalDate datePublishing, String downloadLink) {
        this.name = name;
        this.description = description;
        this.publishingHouse = publishingHouse;
        this.datePublishing = datePublishing;
        this.downloadLink = downloadLink;
    }

    public Book(String name, String description, String publishingHouse, LocalDate datePublishing, String downloadLink, Set<Author> authors, Set<Genre> genres) {
        this.name = name;
        this.description = description;
        this.publishingHouse = publishingHouse;
        this.datePublishing = datePublishing;
        this.downloadLink = downloadLink;
        this.authors = authors;
        this.genres = genres;
    }
}
