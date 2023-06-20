package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.repository.AuthorRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public Author addAuthor(Author author){
        if (author.getName() == null || author.getName().isBlank()){
            throw new AuthorNameIsEmptyException("author name is empty, please provide a name");
        }
        if (author.getBirthDate() == null){
            throw new AuthorBirthDateIsNullException();
        }
        else if (author.getBirthDate().isAfter(LocalDate.now())){
            throw new AuthorBirthDateIsInFutureException();
        }
        return authorRepo.save(author);
    }

    public List<Author> getAuthors(){
        return authorRepo.findAll();
    }

    public Optional<Author> findAuthorById(int id){
        return authorRepo.findById(id);
    }

    public Optional<Author> findAuthorByName(String name){
        return authorRepo.findByName(name);
    }

    public void deleteAuthorById(int id){
        findAuthorById(id).ifPresent(author -> authorRepo.delete(author));
    }

    public void saveAuthor(Author author) {
        authorRepo.save(author);
    }
}
