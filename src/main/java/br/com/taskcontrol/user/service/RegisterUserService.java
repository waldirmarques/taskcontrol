package br.com.taskcontrol.user.service;

import br.com.taskcontrol.user.dto.UserRegisterDTO;

@FunctionalInterface
public interface RegisterUserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
