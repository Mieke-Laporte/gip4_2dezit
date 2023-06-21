package ucll.gip.gip4_2dezit.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAllreadyExistsException extends RuntimeException{
}
