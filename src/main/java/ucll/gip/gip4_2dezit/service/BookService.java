package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.repository.BookRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public Book addBook(Book book){
        return bookRepo.save(book);
    }

    public List<Book> getBooks(){
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(String id){
        return bookRepo.findById(id);
    }

    public void deleteBook(Book book){
        bookRepo.delete(book);
    }

    public void deleteBookById(String id){
        if (getBookById(id).isPresent()){
            bookRepo.delete(getBookById(id).get());
        }
    }
}
