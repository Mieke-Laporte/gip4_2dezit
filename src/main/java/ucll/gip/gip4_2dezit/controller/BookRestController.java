package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.dtos.BookWithUserDTO;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.dtos.BookDTO;
import ucll.gip.gip4_2dezit.service.exceptions.BookAllreadyExistsException;
import ucll.gip.gip4_2dezit.service.exceptions.BookIsbnNumberIsEmptyException;
import ucll.gip.gip4_2dezit.service.BookService;
import ucll.gip.gip4_2dezit.service.exceptions.BookNotFoundException;
import ucll.gip.gip4_2dezit.service.exceptions.BookTitleIsEmptyException;

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

    @PutMapping("/updateBook/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable("id") String id, @RequestBody BookDTO bookDTO){
        try {
            BookDTO res = bookService.updateBook(id, bookDTO);
            return ResponseEntity.ok(res);
        } catch (BookIsbnNumberIsEmptyException e){
            return ResponseEntity.badRequest().body("ISBN number should not be empty");
        } catch (BookNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (BookTitleIsEmptyException e){
            return ResponseEntity.badRequest().body("title should not be empty");
        } catch (BookAllreadyExistsException e){
            return ResponseEntity.badRequest().body("Another book with isbn number " + bookDTO.getIsbnNumber() + " already exists");
        } catch (RuntimeException e){
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

    @GetMapping("/getBookByISBN/{id}")
    public ResponseEntity<Object> getBookByISBN(@PathVariable("id") String id){
        try {
            Optional<Book> optionalBook = bookService.getBookById(id);
            if (optionalBook.isPresent()) {
                Book book = optionalBook.get();
                BookWithUserDTO bookWithUserDTO = new BookWithUserDTO();
                bookWithUserDTO.setIsbnNumber(book.getIsbnNumber());
                bookWithUserDTO.setDescription(book.getDescription());
                bookWithUserDTO.setTitle(book.getTitle());
                if (book.getAuthor() != null) bookWithUserDTO.setAuthorName(book.getAuthor().getName());
                if (book.getUser() != null) bookWithUserDTO.setUserName(book.getUser().getName());
                return ResponseEntity.ok(bookWithUserDTO);
            }
            else return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/deleteBookById/{id}")
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