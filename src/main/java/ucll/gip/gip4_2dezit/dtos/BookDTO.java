package ucll.gip.gip4_2dezit.dtos;

import ucll.gip.gip4_2dezit.model.Book;

public class BookDTO {
    private String isbnNumber;
    private String title;
    private String authorName;
    private String description;

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book toBook(){
        Book book = new Book();
        book.setIsbnNumber(isbnNumber);
        book.setTitle(title);
        book.setDescription(description);
        return book;
    }

    public BookDTO(String isbnNumber, String title, String authorName, String description) {
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.authorName = authorName;
        this.description = description;
    }

    public BookDTO() {

    }

    public BookDTO(String isbnNumber, String title, String description) {
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.description = description;
    }
}
