package din.kz.mind_forge_back.repository;

import din.kz.mind_forge_back.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    boolean existsByName(String role);
}
