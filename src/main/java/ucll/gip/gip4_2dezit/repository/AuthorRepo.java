package ucll.gip.gip4_2dezit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.gip.gip4_2dezit.model.Author;

import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
}
