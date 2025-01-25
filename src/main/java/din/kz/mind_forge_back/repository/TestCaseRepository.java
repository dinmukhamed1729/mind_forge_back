package din.kz.mind_forge_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import din.kz.mind_forge_back.model.entity.TestCase;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}