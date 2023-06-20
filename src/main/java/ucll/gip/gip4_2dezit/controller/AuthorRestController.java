package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.requests.CreateAuthorRequest;
import ucll.gip.gip4_2dezit.service.AuthorNameIsEmptyException;
import ucll.gip.gip4_2dezit.service.AuthorService;
import ucll.gip.gip4_2dezit.service.BookService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<Object> addAuthor (@RequestBody CreateAuthorRequest createAuthorRequest){
        try {
            Author author = authorService.addAuthor(createAuthorRequest.toAuthor());
            return ResponseEntity.ok(author);
        } catch (AuthorNameIsEmptyException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAuthors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/getAuthorById/{id}")
    public Optional<Author> getAuthorById(@PathVariable("id") int id){
        return authorService.findAuthorById(id);
    }

    @PostMapping("/deleteAuthorById/{id}")
    public void deleteAuthorById(@PathVariable("id") int id){
        authorService.deleteAuthorById(id);
    }
}
