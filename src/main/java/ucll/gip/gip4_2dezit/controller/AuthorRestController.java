package ucll.gip.gip4_2dezit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucll.gip.gip4_2dezit.dtos.AuthorListItemDTO;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.dtos.AuthorDTO;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.service.exceptions.AuthorBirthDateIsInFutureException;
import ucll.gip.gip4_2dezit.service.exceptions.AuthorBirthDateIsNullException;
import ucll.gip.gip4_2dezit.service.exceptions.AuthorNameIsEmptyException;
import ucll.gip.gip4_2dezit.service.AuthorService;
import ucll.gip.gip4_2dezit.service.exceptions.AuthorNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public ResponseEntity<Object> addAuthor (@RequestBody AuthorDTO authorDTO){
        try {
            Author author = authorService.addAuthor(authorDTO.toAuthor());
            return ResponseEntity.ok(author);
        } catch (AuthorNameIsEmptyException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateAuthor/{id}")
    public ResponseEntity<Object> updateAuthor(@PathVariable("id") int id, @RequestBody AuthorDTO authorDTO){
        try{
            Author author = authorService.updateAuthor(id, authorDTO.toAuthor());
            AuthorListItemDTO dto = new AuthorListItemDTO();
            dto.setId(author.getId());
            dto.setName(author.getName());
            dto.setBirthDate(author.getBirthDate().toString());
            dto.setBiography(author.getBiography());
            for (Book book : author.getBooks()){
                dto.addBook(book.getIsbnNumber() + ", " + book.getTitle());
            }
            return ResponseEntity.ok(dto);
        } catch (AuthorNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (AuthorNameIsEmptyException e){
            return ResponseEntity.badRequest().body("name should not be empty");
        } catch (AuthorBirthDateIsNullException e){
            return ResponseEntity.badRequest().body("birthdate should not be empty");
        } catch (AuthorBirthDateIsInFutureException e){
            return ResponseEntity.badRequest().body("birthdate should not be in the future");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }
    }

    @GetMapping("/getAuthors")
    public ResponseEntity<Object> getAuthors(){
        try {
            List<AuthorListItemDTO> authorListItemDTOS = authorService.getAuthors();
            return ResponseEntity.ok(authorListItemDTOS);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAuthorById/{id}")
    public ResponseEntity<Object> getAuthorById(@PathVariable("id") int id){
        try {
            Optional<Author> author = authorService.findAuthorById(id);
            AuthorListItemDTO authorListItemDTO = new AuthorListItemDTO();
            if (author.isPresent()){
                authorListItemDTO.setId(author.get().getId());
                authorListItemDTO.setName(author.get().getName());
                authorListItemDTO.setBirthDate(author.get().getBirthDate().toString());
                authorListItemDTO.setBiography(author.get().getBiography());
                for (Book book : author.get().getBooks()){
                    authorListItemDTO.addBook(book.getIsbnNumber() + ", " + book.getTitle());
                }
                return ResponseEntity.ok(authorListItemDTO);
            }
            return ResponseEntity.badRequest().body("author is empty");
        } catch (AuthorNotFoundException e){
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAuthorById/{id}")
    public ResponseEntity<Object> deleteAuthorById(@PathVariable("id") int id){
        try {
            authorService.deleteAuthorById(id);
            return ResponseEntity.ok("author with id " + id + " is deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Message: " + e.getMessage());
        }
    }
}
