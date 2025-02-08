package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.User;
import din.kz.mind_forge_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public void createUser(User user) {
        user.setRoles(Set.of(roleService.getUserRole()));
        userRepository.save(user);
    }

    public boolean userWithUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}

