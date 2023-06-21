package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.dtos.AuthorListItemDTO;
import ucll.gip.gip4_2dezit.model.Author;
import ucll.gip.gip4_2dezit.model.Book;
import ucll.gip.gip4_2dezit.repository.AuthorRepo;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public List<AuthorListItemDTO> getAuthors(){
        List<Author> authorList = authorRepo.findAll();
        List<AuthorListItemDTO> authorListItemDTOS = new ArrayList<AuthorListItemDTO>();
        for (Author author: authorList) {
            AuthorListItemDTO authorListItemDTO = new AuthorListItemDTO();
            authorListItemDTO.setId(author.getId());
            authorListItemDTO.setName(author.getName());
            authorListItemDTO.setBiography(author.getBiography());
            authorListItemDTO.setBirthDate(author.getBirthDate().toString());
            for (Book book: author.getBooks()) {
                authorListItemDTO.addBook(book.getIsbnNumber() + ", " + book.getTitle());
            }
            authorListItemDTOS.add(authorListItemDTO);
        }
        return authorListItemDTOS;
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
