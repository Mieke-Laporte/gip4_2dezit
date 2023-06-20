package ucll.gip.gip4_2dezit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @NotEmpty(message = "Please provide an ISBN number")
    private String isbnNumber;

    @NotEmpty(message = "Please provide a title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
