package din.kz.mind_forge_back.repository;

import din.kz.mind_forge_back.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
}
