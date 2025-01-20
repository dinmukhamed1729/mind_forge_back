package din.kz.mind_forge_back.service;

import din.kz.mind_forge_back.model.entity.Role;
import din.kz.mind_forge_back.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("USER");
    }
}
