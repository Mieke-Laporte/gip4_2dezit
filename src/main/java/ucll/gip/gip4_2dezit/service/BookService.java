package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.model.User;
import ucll.gip.gip4_2dezit.repository.BookRepo;
import ucll.gip.gip4_2dezit.dtos.BookDTO;
import ucll.gip.gip4_2dezit.service.exceptions.BookAllreadyExistsException;
import ucll.gip.gip4_2dezit.service.exceptions.BookIsbnNumberIsEmptyException;
import ucll.gip.gip4_2dezit.service.exceptions.BookTitleIsEmptyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService userService;

    public BookDTO addBook(BookDTO bookDTO) {
        if (bookDTO.getTitle() == null || bookDTO.getTitle().isBlank()) {
            throw new BookTitleIsEmptyException();
        }
        if (bookDTO.getIsbnNumber() == null || bookDTO.getIsbnNumber().isBlank()) {
            throw new BookIsbnNumberIsEmptyException();
        }
        else if (getBookById(bookDTO.getIsbnNumber()).isPresent()){
            throw new BookAllreadyExistsException();
        }
        Book book = bookDTO.toBook();
        Optional<Author> optionalAuthor = authorService.findAuthorByName(bookDTO.getAuthorName());
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            book.setAuthor(author);
            author.addBook(book);
        }

        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userService.findUserByUsername(currentUserName);
        if (optionalUser.isPresent()){
            book.setUser(optionalUser.get());
            optionalUser.get().addBook(book);
        }

        Book book1 = bookRepo.save(book);
        optionalAuthor.ifPresent(author -> authorService.saveAuthor(author));
        optionalUser.ifPresent(user -> userService.saveUser(user));
        BookDTO bookDTO1 = new BookDTO();
        bookDTO1.setIsbnNumber(book1.getIsbnNumber());
        bookDTO1.setTitle(book1.getTitle());
        bookDTO1.setDescription(book1.getDescription());
        optionalAuthor.ifPresent(author -> {
            bookDTO1.setAuthorName(author.getName());
        });

        return bookDTO1;
    }

    public List<BookDTO> getBooks() {
        List<Book> books = bookRepo.findAll();
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book: books){
            if (book.getAuthor() != null){
                bookDTOS.add(new BookDTO(book.getIsbnNumber(), book.getTitle(), book.getAuthor().getName(), book.getDescription()));
            } else bookDTOS.add(new BookDTO(book.getIsbnNumber(), book.getTitle(), book.getDescription()));
        }
        return bookDTOS;
    }

    public Optional<Book> getBookById(String id) {
        return bookRepo.findById(id);
    }

    private void deleteBook(Book book) {
        bookRepo.delete(book);
    }

    public void deleteBookById(String id) {
        if (getBookById(id).isPresent()) {
            bookRepo.delete(getBookById(id).get());
        }
    }
}
