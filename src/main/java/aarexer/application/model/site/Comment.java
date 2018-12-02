package aarexer.application.model.site;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends AuditModel {
    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    @Lob
    @NotNull
    private String content;

    public Comment(String content) {
        this.content = content;
    }
}
