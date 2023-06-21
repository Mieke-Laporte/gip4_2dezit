package ucll.gip.gip4_2dezit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.gip.gip4_2dezit.dtos.UserDTOItem;
import ucll.gip.gip4_2dezit.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;
    public int getAmountOfPublishers(){
        List<UserDTOItem> userList = userService.getAllUsers();
        int result = 0;
        for (UserDTOItem user: userList) {
            if (user.getRole().contains("UITGEVER")){
                result++;
            }
        }
        return result;
    }

    public int getAmountOfBooks(){
        return bookService.getBooks().size();
    }

    public List<String> getAmountOfBooksPerPublisher(){
        List<UserDTOItem> userDTOItems = userService.getAllUsers();
        List<UserDTOItem> publishers = userDTOItems.stream().filter(userDTOItem -> userDTOItem.getRole().contains("UITGEVER")).toList();
        List<String> results = new ArrayList<>();
        for (UserDTOItem publisher: publishers){
            results.add(publisher.getName() + ": " + publisher.getBookStrings().size());
        }
        return results;
    }

    public double getAvarageBooksPerPublisher(){
        List<UserDTOItem> userDTOItems = userService.getAllUsers();
        List<UserDTOItem> publishers = userDTOItems.stream().filter(userDTOItem -> userDTOItem.getRole().contains("UITGEVER")).toList();
        int totalPublishers = publishers.size();
        int totalbooks = publishers.stream().mapToInt(value -> value.getBookStrings().size()).sum();
        if (totalPublishers > 0){
            return (double) totalbooks/totalPublishers;
        }
        return 0;
    }
}
