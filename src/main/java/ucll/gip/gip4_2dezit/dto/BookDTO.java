package ucll.gip.gip4_2dezit.dto;

import ucll.gip.gip4_2dezit.model.Author;

public class BookDTO {
    private String title;
    private AuthorDTO author;
    private String isbnNumber;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
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

    public BookDTO(String title, AuthorDTO author, String isbnNumber, String description) {
        setTitle(title);
        setAuthor(author);
        setIsbnNumber(isbnNumber);
        setDescription(description);
    }

}
