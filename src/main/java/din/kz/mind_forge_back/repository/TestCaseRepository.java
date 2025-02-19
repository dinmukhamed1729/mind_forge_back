package din.kz.mind_forge_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import din.kz.mind_forge_back.model.entity.TestCase;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

    Optional<Set<TestCase>> findAllByTaskIdAndIsPublicTrue(Long id);
}