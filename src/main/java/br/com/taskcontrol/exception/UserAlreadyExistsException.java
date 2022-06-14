package br.com.taskcontrol.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -2155157191257995726L;

    private static final String USER_ALREADY_EXISTS = "User already exists!";

    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS);
    }
}
