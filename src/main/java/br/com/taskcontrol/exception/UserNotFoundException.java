package br.com.taskcontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2155157191257995726L;

    private static final String USER_NOT_FOUND = "User not found!";

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
