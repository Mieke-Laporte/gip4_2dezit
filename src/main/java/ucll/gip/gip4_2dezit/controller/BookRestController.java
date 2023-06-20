package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.requests.CreateBookRequest;
import ucll.gip.gip4_2dezit.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public Book addBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        return bookService.addBook(createBookRequest);
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/getBookById/{id}")
    public Optional<Book> getBookById(@PathVariable("id") String id){
        return bookService.getBookById(id);
    }

    @PostMapping("/deleteBookById/{id}")
    public void deleteBookById(@PathVariable("id") String id){
        bookService.deleteBookById(id);
    }
} 