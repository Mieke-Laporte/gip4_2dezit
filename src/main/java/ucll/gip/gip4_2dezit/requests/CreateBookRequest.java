package ucll.gip.gip4_2dezit.requests;

import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.service.AuthorService;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

public class CreateBookRequest {
    private AuthorService authorService;
    private String isbnNumber;
    private String title;
    private String authorName;
    private String description;

    public Book toBook(){
        Book book = new Book();
        book.setIsbnNumber(isbnNumber);
        book.setTitle(title);
        book.setDescription(description);
        if (authorService.findAuthorByName(authorName).isPresent()){
            Author author = authorService.findAuthorByName(authorName).get();
            book.setAuthor(author);
        }
        return book;
    }
}
