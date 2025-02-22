package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.exception.PasswordMismatchException;
import din.kz.mind_forge_back.exception.UserAlreadyExistsException;
import din.kz.mind_forge_back.mapper.UserMapper;
import din.kz.mind_forge_back.model.entity.User;
import din.kz.mind_forge_back.model.request.RegistrationRequest;
import din.kz.mind_forge_back.model.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserMapper userMapper;

    public RegistrationResponse registration(RegistrationRequest registrationRequest) {

        log.info("Registration request username: {}", registrationRequest.getUsername());
        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
            throw new PasswordMismatchException();
        }
        if (userService.userWithUsernameExist(registrationRequest.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        var user = userMapper.registrationRequestToUser(registrationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return userMapper.toRegistrationResponse(user);
    }
}
