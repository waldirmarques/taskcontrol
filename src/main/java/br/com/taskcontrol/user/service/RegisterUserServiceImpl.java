package br.com.taskcontrol.user.service;

import br.com.taskcontrol.exception.UserAlreadyExistsException;
import br.com.taskcontrol.user.User;
import br.com.taskcontrol.user.UserRepository;
import br.com.taskcontrol.user.dto.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserServiceImpl implements RegisterUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (this.userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        var user = User.from(userRegisterDTO);
        user.setPassword(bCryptPasswordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);
    }
}
