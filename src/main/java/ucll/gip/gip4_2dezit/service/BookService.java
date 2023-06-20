package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.repository.BookRepo;
import ucll.gip.gip4_2dezit.requests.CreateBookRequest;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorService authorService;

    public Book addBook(CreateBookRequest createBookRequest){
        if (createBookRequest.getTitle() == null || createBookRequest.getTitle().isBlank()){
            throw new BookTitleIsEmptyException();
        }
        if (createBookRequest.getIsbnNumber() == null || createBookRequest.getIsbnNumber().isBlank()){
            throw new BookIsbnNumberIsEmptyException();
        }
        Book book = createBookRequest.toBook();
        Optional<Author> optionalAuthor = authorService.findAuthorByName(createBookRequest.getAuthorName());
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.addBook(book); // Use the addBook method in Author class
            authorService.saveAuthor(author); // Save the updated author entity
        }
        return bookRepo.save(book);
    }

    public List<Book> getBooks(){
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(String id){
        return bookRepo.findById(id);
    }

    private void deleteBook(Book book){
        bookRepo.delete(book);
    }

    public void deleteBookById(String id){
        if (getBookById(id).isPresent()){
            bookRepo.delete(getBookById(id).get());
        }
    }
}
