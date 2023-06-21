package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.dtos.BookDTO;
import ucll.gip.gip4_2dezit.service.BookAllreadyExistsException;
import ucll.gip.gip4_2dezit.service.BookIsbnNumberIsEmptyException;
import ucll.gip.gip4_2dezit.service.BookService;
import ucll.gip.gip4_2dezit.service.BookTitleIsEmptyException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("books")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@Valid @RequestBody BookDTO bookDTO){
        try {
            BookDTO bookDTOResult = bookService.addBook(bookDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(bookDTOResult);
        } catch (BookTitleIsEmptyException e){
            return ResponseEntity.badRequest().body("Book title cannot be empty");
        } catch (BookIsbnNumberIsEmptyException e){
            return ResponseEntity.badRequest().body("Book ISBN number cannot be empty");
        } catch (BookAllreadyExistsException e){
            return ResponseEntity.badRequest().body("Book with ISBN number " + bookDTO.getIsbnNumber() + "already exists");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBooks")
    public ResponseEntity<Object> getBooks(){
        try {
            List<BookDTO> bookDTOS = bookService.getBooks();
            return ResponseEntity.ok(bookDTOS);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") String id){
        try {
            Optional<Book> optionalBook = bookService.getBookById(id);
            if (optionalBook.isPresent()) return ResponseEntity.ok(optionalBook.get());
            else return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/deleteBookById/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable("id") String id){
        try{
            bookService.deleteBookById(id);
            if (bookService.getBookById(id).isEmpty()){
                return ResponseEntity.ok("Book with id " + id + " is deleted");
            } else return ResponseEntity.badRequest().body("Something went wrong, book is not deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
} 