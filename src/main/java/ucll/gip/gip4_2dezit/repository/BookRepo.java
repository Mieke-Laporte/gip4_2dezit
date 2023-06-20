package ucll.gip.gip4_2dezit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucll.gip.gip4_2dezit.model.Book;

public interface BookRepo extends JpaRepository<Book,String> {
}
